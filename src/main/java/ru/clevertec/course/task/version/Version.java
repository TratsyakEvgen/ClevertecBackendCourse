package ru.clevertec.course.task.version;

public interface Version {
    void increaseMajorVersion(int value);

    void increaseMinorVersion(int value);

    String toString();
}
