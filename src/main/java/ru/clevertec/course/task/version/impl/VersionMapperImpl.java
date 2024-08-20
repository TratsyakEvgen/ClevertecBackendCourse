package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionMapperImpl implements VersionMapper {

    @Override
    public Version parse(String stringVersion) {

        if (stringVersion == null) {
            throw new IllegalArgumentException("String version must not be null");
        }

        String majorAndMinorSubstring = getMajorAndMinorSubstring(stringVersion);
        String[] splitMajorAndMinor = majorAndMinorSubstring.split("\\.");
        int major = Integer.parseInt(splitMajorAndMinor[0]);
        int minor = Integer.parseInt(splitMajorAndMinor[1]);

        return new Version(major, minor);
    }

    private String getMajorAndMinorSubstring(String stringVersion) {

        Pattern pattern = Pattern.compile("(\\d+\\.)+\\d+");
        Matcher matcher = pattern.matcher(stringVersion);
        if (matcher.find()) {
            return stringVersion.substring(matcher.start(), matcher.end());
        }
        throw new IllegalArgumentException("Incorrect format! String has not major or minor version");

    }


}
