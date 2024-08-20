package ru.clevertec.course.task;

import org.gradle.api.tasks.TaskAction;


public class TagController extends GitTask {

    @TaskAction
    public void check() {

        boolean isCommitContainsTag = !commandExecutor.getResult("git", "tag", "--contains").isEmpty();
        if (isCommitContainsTag) {
            log.warn("Current commit already has tag!");
            skipAllNextTasks();
        }

    }
}
