package ru.clevertec.course.task.version;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Version {

    protected final int major;
    protected final int minor;
    protected final String postfix;

    public Version(int major, int minor) {
        this(major, minor, "");
    }

    public Version(int major, int minor, String postfix) {
        this.major = major;
        this.minor = minor;
        this.postfix = postfix;
    }

    @Override
    public String toString() {
        return "v" + major + "." + minor + postfix;
    }
}
