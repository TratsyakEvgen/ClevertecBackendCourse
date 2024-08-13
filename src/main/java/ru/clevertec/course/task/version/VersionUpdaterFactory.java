package ru.clevertec.course.task.version;

public interface VersionUpdaterFactory {
    VersionUpdater getVersionUpdater(String name);
}
