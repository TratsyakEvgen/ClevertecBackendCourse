package ru.clevertec.course.task.version.impl.updater;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionUpdater;

public class MasterVersionUpdater implements VersionUpdater {
    @Override
    public Version update(Version version) {
        int major = version.getMajor();

        major++;
        return new Version(major, 0);
    }
}
