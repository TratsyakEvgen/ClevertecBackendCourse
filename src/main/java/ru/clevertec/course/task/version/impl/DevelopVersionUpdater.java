package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class DevelopVersionUpdater implements VersionUpdater {
    @Override
    public String update(Version version) {
        version.increaseMinorVersion(1);
        return version.toString();
    }
}
