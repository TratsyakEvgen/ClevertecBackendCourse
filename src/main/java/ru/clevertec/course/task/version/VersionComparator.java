package ru.clevertec.course.task.version;

import java.util.Comparator;

public class VersionComparator implements Comparator<Version> {
    @Override
    public int compare(Version o1, Version o2) {
        int compareMajor = Integer.compare(o1.getMajor(), o2.getMajor());
        if (compareMajor == 0) {
            return Integer.compare(o1.getMinor(), o2.getMinor());

        }
        return compareMajor;
    }
}
