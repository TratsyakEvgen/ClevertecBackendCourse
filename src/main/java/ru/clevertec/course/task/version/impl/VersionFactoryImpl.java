package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionFactory;
import ru.clevertec.course.task.version.VersionMapper;

import java.util.Comparator;
import java.util.List;

public class VersionFactoryImpl implements VersionFactory {

    private final VersionMapper versionMapper;

    private final Comparator<Version> comparator;

    public VersionFactoryImpl(VersionMapper versionMapper, Comparator<Version> comparator) {
        this.versionMapper = versionMapper;
        this.comparator = comparator;
    }

    @Override
    public Version getLastVersion(List<String> strings) {
        return strings.stream()
                .map(versionMapper::parse)
                .max(comparator)
                .orElseGet(() -> new Version(0, 0));

    }
}
