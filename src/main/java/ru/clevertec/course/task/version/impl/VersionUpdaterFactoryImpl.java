package ru.clevertec.course.task.version.impl;

import ru.clevertec.course.task.version.VersionUpdater;
import ru.clevertec.course.task.version.VersionUpdaterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class VersionUpdaterFactoryImpl implements VersionUpdaterFactory {

    private static final Map<String, Supplier<VersionUpdater>> SUPPLIER_MAP = new HashMap<>();

    static {
        SUPPLIER_MAP.put("master", MasterVersionUpdater::new);
        SUPPLIER_MAP.put("dev", DevelopVersionUpdater::new);
        SUPPLIER_MAP.put("stage", StageVersionUpdater::new);
        SUPPLIER_MAP.put("qa", QaVersionUpdater::new);
    }

    @Override
    public VersionUpdater getVersionUpdater(String name) {
        Supplier<VersionUpdater> versionUpdaterSupplier = SUPPLIER_MAP.get(name);
        if (versionUpdaterSupplier == null) {
            return new SnapshotVersionUpdater();
        }
        return versionUpdaterSupplier.get();
    }
}
