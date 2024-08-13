package ru.clevertec.course.task.commad.impl;

import ru.clevertec.course.task.commad.CommandExecutor;
import ru.clevertec.course.task.commad.CommandExecutorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CommandExecutorImpl implements CommandExecutor {
    @Override
    public List<String> getResult(String... command) {
        InputStream inputStream = getInputStreamOfProcess(command);

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new CommandExecutorException("Can not read result of process", e);
        }
    }

    private InputStream getInputStreamOfProcess(String[] command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            return process.getInputStream();
        } catch (InterruptedException | IOException e) {
            throw new CommandExecutorException("Can not execute runtime process", e);
        }
    }
}
