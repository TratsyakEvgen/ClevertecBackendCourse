package ru.clevertec.course.task.commad;

public class CommandExecutorException extends RuntimeException {
    public CommandExecutorException() {
    }

    public CommandExecutorException(String message) {
        super(message);
    }

    public CommandExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecutorException(Throwable cause) {
        super(cause);
    }

    public CommandExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
