package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class StageVersionUpdater implements VersionUpdater {
    @Override
    public String update(Version version) {
        version.increaseMinorVersion(3);
        return version + "-rc";
    }
}
