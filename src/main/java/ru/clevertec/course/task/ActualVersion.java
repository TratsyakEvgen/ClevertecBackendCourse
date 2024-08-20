package ru.clevertec.course.task;

import org.gradle.api.tasks.TaskAction;
import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdaterFactory;
import ru.clevertec.course.task.version.impl.VersionUpdaterFactoryImpl;


public class ActualVersion extends GitTask {
    private final VersionUpdaterFactory versionUpdaterFactory = new VersionUpdaterFactoryImpl();

    @TaskAction
    public void getCurrentVersion() {
        String branch = commandExecutor.getResult("git", "branch", "--show-current").getFirst();
        log.warn("Current brunch: " + branch);

        Version currentVersion = (Version) getProjectExtensions().findByName("currentVersion");

        Version actualVersion = versionUpdaterFactory.getVersionUpdater(branch).update(currentVersion);
        log.warn("Actual version: " + actualVersion);

        getProjectExtensions().create("actualVersion", Version.class, actualVersion.getMajor(), actualVersion.getMinor());
    }


}