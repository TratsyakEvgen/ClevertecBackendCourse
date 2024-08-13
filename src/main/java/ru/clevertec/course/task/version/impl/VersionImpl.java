package ru.clevertec.course.task.version.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.clevertec.course.task.version.Version;


@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class VersionImpl implements Version {
    private int major;
    private int minor;

    @Override
    public void increaseMajorVersion(int value) {
        major = major + value;
        minor = 0;
    }

    @Override
    public void increaseMinorVersion(int value) {
        minor = minor + value;
    }

    @Override
    public String toString() {
        return ("v" + major + "." + minor);
    }

}
