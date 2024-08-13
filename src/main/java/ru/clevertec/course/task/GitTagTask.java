package ru.clevertec.course.task;


import lombok.extern.slf4j.Slf4j;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;
import ru.clevertec.course.task.commad.CommandExecutor;
import ru.clevertec.course.task.commad.impl.CommandExecutorImpl;
import ru.clevertec.course.task.version.Version;
import ru.clevertec.course.task.version.VersionFactory;
import ru.clevertec.course.task.version.VersionUpdaterFactory;
import ru.clevertec.course.task.version.impl.VersionFactoryImpl;
import ru.clevertec.course.task.version.impl.VersionUpdaterFactoryImpl;

import java.util.List;

@Slf4j
public class GitTagTask extends DefaultTask {

    private final CommandExecutor commandExecutor = new CommandExecutorImpl();
    private final VersionFactory versionFactory = new VersionFactoryImpl();
    private final VersionUpdaterFactory versionUpdaterFactory = new VersionUpdaterFactoryImpl();
    private Version lastVersion;
    private String currentBrunch;
    private String actualVersion;

    @TaskAction
    public void assignTag() {
        throwIfLocalRepositoryNotExists();

        setLastVersion();

        if (!isExistUncommittedFiles() && !isCommitContainsTag()) {

            setCurrentBranch();
            setActualVersion();
            publishActualVersion();

            if (isRemoteRepositoryExists()) {
                pushActualVersion();
            }
        }
    }

    private void throwIfLocalRepositoryNotExists() {
        List<String> lines = commandExecutor.getResult("git", "rev-parse", "--is-inside-work-tree");
        if (lines.isEmpty()) {
            throw new GradleException("Git repository not exists");
        }
    }

    private void setLastVersion() {
        List<String> lines = commandExecutor.getResult("git", "tag");
        lastVersion = versionFactory.getLastVersion(lines);
        log.warn("Last version: " + lastVersion);
    }


    private boolean isExistUncommittedFiles() {
        boolean isExistUncommittedFiles = commandExecutor.getResult("git", "status")
                .stream()
                .anyMatch(string -> string.contains("modified"));
        if (isExistUncommittedFiles) {
            log.warn("There are uncommitted files, current version: " + lastVersion + ".uncommitted");
        }
        return isExistUncommittedFiles;
    }

    private boolean isCommitContainsTag() {
        boolean isCommitContainsTag = !commandExecutor.getResult("git", "tag", "--contains").isEmpty();
        if (isCommitContainsTag) {
            log.warn("Current commit has tag!");
        }
        return isCommitContainsTag;
    }

    private void setCurrentBranch() {
        currentBrunch = commandExecutor.getResult("git", "branch", "--show-current").getFirst();
        log.warn("Current brunch: " + currentBrunch);
    }

    private void setActualVersion() {
        actualVersion = versionUpdaterFactory.getVersionUpdater(currentBrunch).update(lastVersion);
        log.warn("Actual version: " + actualVersion);
    }

    private void publishActualVersion() {
        commandExecutor.getResult("git", "tag", "-a", actualVersion, "-m", "This tag published using gradle plugin");
        log.warn("Published tag: " + actualVersion);
    }

    private boolean isRemoteRepositoryExists() {
        boolean isRemoteRepositoryExists = !commandExecutor.getResult("git", "remote").isEmpty();
        if (!isRemoteRepositoryExists) {
            log.warn("Can not push tag, because remote repository don't exists");
        }
        return isRemoteRepositoryExists;
    }

    private void pushActualVersion() {
        commandExecutor.getResult("git", "push", "origin", "tag", actualVersion);
        log.warn("Tag pushed!");
    }

}
