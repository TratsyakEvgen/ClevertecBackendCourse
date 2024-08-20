package ru.clevertec.course.task.version.impl.updater;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class DevelopVersionUpdater implements VersionUpdater {
    @Override
    public Version update(Version version) {
        int major = version.getMajor();
        int minor = version.getMinor();

        minor++;
        return new Version(major, minor);
    }
}
