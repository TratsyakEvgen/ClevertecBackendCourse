package ru.clevertec.course.task.version.impl.updater;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class SnapshotVersionUpdater implements VersionUpdater {
    @Override
    public Version update(Version version) {
        int major = version.getMajor();
        int minor = version.getMinor();

        return new Version(major, minor, "-SNAPSHOT");
    }
}
