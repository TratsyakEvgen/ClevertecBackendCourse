package ru.clevertec.course.task.commad;

import java.util.List;

public interface CommandExecutor {
    List<String> getResult(String... command);
}
