package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class SnapshotVersionUpdater implements VersionUpdater {
    @Override
    public String update(Version version) {
        return version.toString() + "-SNAPSHOT";
    }
}
