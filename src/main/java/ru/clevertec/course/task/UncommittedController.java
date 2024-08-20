package ru.clevertec.course.task;

import org.gradle.api.tasks.TaskAction;
import ru.clevertec.course.task.version.Version;

public class UncommittedController extends GitTask {
    @TaskAction
    public void getCurrentVersion() {
        boolean isExistUncommittedFiles = commandExecutor.getResult("git", "status")
                .stream()
                .anyMatch(string -> string.contains("modified"));

        if (isExistUncommittedFiles) {
            Version currentVersion = (Version) getProjectExtensions().findByName("currentVersion");
            log.warn("There are uncommitted files, current version: " + currentVersion + ".uncommitted");
            skipAllNextTasks();
        }

    }

}
