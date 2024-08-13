package ru.clevertec.course;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import ru.clevertec.course.task.GitTagTask;

public class GitTagPlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        target.getTasks().create("gitTag", GitTagTask.class);
    }
}