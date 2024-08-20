package ru.clevertec.course.task;

import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.util.List;


public class RemoteRepositoryController extends GitTask {
    @TaskAction
    public void check() {
        List<String> lines = commandExecutor.getResult("git", "remote");
        if (lines.isEmpty()) {
            throw new GradleException("Remote repository not exists");
        }
    }
}
