package ru.clevertec.course.task;

import org.gradle.api.tasks.TaskAction;
import ru.clevertec.course.task.version.Version;

public class Publisher extends GitTask {

    @TaskAction
    public void getCurrentVersion() {
        Version actualVersion = (Version) getProjectExtensions().findByName("actualVersion");

        commandExecutor.getResult("git", "tag", "-a", actualVersion.toString(), "-m", "This tag published using gradle plugin");
        commandExecutor.getResult("git", "push", "origin", "tag", actualVersion.toString());
        log.warn("Tag pushed!");
    }

}
