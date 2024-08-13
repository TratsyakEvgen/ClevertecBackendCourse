package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class MasterVersionUpdater implements VersionUpdater {
    @Override
    public String update(Version version) {
        version.increaseMajorVersion(1);
        return version.toString();
    }
}
