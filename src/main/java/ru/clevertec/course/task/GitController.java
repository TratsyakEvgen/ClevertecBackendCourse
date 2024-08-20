package ru.clevertec.course.task;

import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.util.List;

public class GitController extends GitTask {

    @TaskAction
    public void check() {
        List<String> lines = commandExecutor.getResult("git", "--version");
        if (lines.isEmpty()) {
            throw new GradleException("Git not exists");
        }
        log.warn(lines.getFirst());
    }

}
