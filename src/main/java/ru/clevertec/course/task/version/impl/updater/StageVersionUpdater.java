package ru.clevertec.course.task.version.impl.updater;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class StageVersionUpdater implements VersionUpdater {
    @Override
    public Version update(Version version) {
        int major = version.getMajor();
        int minor = version.getMinor();

        minor = minor + 3;
        return new Version(major, minor, "-rc");
    }
}
