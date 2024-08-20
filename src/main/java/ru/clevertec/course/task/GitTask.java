package ru.clevertec.course.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.Internal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.course.task.commad.CommandExecutor;
import ru.clevertec.course.task.commad.impl.CommandExecutorImpl;

import java.util.Objects;

public class GitTask extends DefaultTask {
    protected static final Logger log = LoggerFactory.getLogger(GitTask.class);
    protected final CommandExecutor commandExecutor = new CommandExecutorImpl();

    protected void skipAllNextTasks() {
        this.getProject().getTasks().stream()
                .filter(task -> Objects.equals(task.getGroup(), this.getGroup()))
                .filter(task -> !task.getState().getExecuted())
                .filter(task -> !task.equals(this))
                .forEach(task1 -> task1.setEnabled(false));
    }

    @Internal
    protected ExtensionContainer getProjectExtensions() {
        return this.getProject().getExtensions();
    }
}
