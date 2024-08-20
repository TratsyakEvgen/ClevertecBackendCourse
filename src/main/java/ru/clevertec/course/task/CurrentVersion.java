package ru.clevertec.course.task;

import org.gradle.api.tasks.TaskAction;
import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionComparator;
import ru.clevertec.course.task.version.VersionFactory;
import ru.clevertec.course.task.version.impl.VersionFactoryImpl;
import ru.clevertec.course.task.version.impl.VersionMapperImpl;

import java.util.List;


public class CurrentVersion extends GitTask {
    private final VersionFactory versionFactory = new VersionFactoryImpl(new VersionMapperImpl(), new VersionComparator());

    @TaskAction
    public void getCurrentVersion() {
        List<String> lines = commandExecutor.getResult("git", "ls-remote", "--tags", "origin");

        Version currentVersion = versionFactory.getLastVersion(lines);
        log.warn("Current version: " + currentVersion);

        getProjectExtensions().create("currentVersion", Version.class, currentVersion.getMajor(), currentVersion.getMinor());
    }

}
