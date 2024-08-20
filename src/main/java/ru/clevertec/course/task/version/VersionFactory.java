package ru.clevertec.course.task.version;

import java.util.List;

public interface VersionFactory {
    Version getLastVersion(List<String> strings);
}
