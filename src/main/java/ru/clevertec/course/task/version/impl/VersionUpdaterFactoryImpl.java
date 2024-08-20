package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.VersionUpdater;
import ru.clevertec.course.task.version.VersionUpdaterFactory;
import ru.clevertec.course.task.version.impl.updater.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class VersionUpdaterFactoryImpl implements VersionUpdaterFactory {

    private static final Map<String, Supplier<VersionUpdater>> VERSION_UPDATER_SUPPLIERS = new HashMap<>();

    static {
        VERSION_UPDATER_SUPPLIERS.put("master", MasterVersionUpdater::new);
        VERSION_UPDATER_SUPPLIERS.put("dev", DevelopVersionUpdater::new);
        VERSION_UPDATER_SUPPLIERS.put("stage", StageVersionUpdater::new);
        VERSION_UPDATER_SUPPLIERS.put("qa", QaVersionUpdater::new);
    }

    @Override
    public VersionUpdater getVersionUpdater(String name) {
        return Optional.ofNullable(VERSION_UPDATER_SUPPLIERS.get(name))
                .orElseGet(() -> SnapshotVersionUpdater::new)
                .get();

    }
}
