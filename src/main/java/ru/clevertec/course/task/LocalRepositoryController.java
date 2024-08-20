package ru.clevertec.course.task;

import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.util.List;

public class LocalRepositoryController extends GitTask {
    @TaskAction
    public void check() {
        List<String> lines = commandExecutor.getResult("git", "rev-parse", "--is-inside-work-tree");
        if (lines.isEmpty()) {
            throw new GradleException("Git repository not exists");
        }
    }
}
