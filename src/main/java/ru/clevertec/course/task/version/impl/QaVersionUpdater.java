package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class QaVersionUpdater implements VersionUpdater {
    @Override
    public String update(Version version) {
        version.increaseMinorVersion(2);
        return version.toString();
    }
}
