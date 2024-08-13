package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionFactory;

import java.util.List;

public class VersionFactoryImpl implements VersionFactory {
    @Override
    public Version getLastVersion(List<String> strings) {
        String[] stringVersionValue = strings.stream()
                .map(line -> {
                    line = line.replace("v", "");
                    return Double.parseDouble(line);
                })
                .max(Double::compare)
                .orElse(0.0)
                .toString()
                .split("\\.");

        return new VersionImpl(Integer.parseInt(stringVersionValue[0]), Integer.parseInt(stringVersionValue[1]));

    }
}
