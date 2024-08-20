package ru.clevertec.course;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import ru.clevertec.course.task.*;

public class GitTagPlugin implements Plugin<Project> {

    private final static String GROUP = "git tag";

    @Override
    public void apply(Project target) {

        GitController gitController = target.getTasks().create("checkGit", GitController.class);
        gitController.setGroup(GROUP);

        LocalRepositoryController localRepositoryController = target.getTasks()
                .create("checkLocalRepository", LocalRepositoryController.class);
        localRepositoryController.setGroup(GROUP);
        localRepositoryController.dependsOn(gitController);

        TagController tagController = target.getTasks().create("checkTag", TagController.class);
        tagController.setGroup(GROUP);
        tagController.dependsOn(localRepositoryController);

        RemoteRepositoryController remoteRepositoryController = target.getTasks()
                .create("checkRemoteRepository", RemoteRepositoryController.class);
        remoteRepositoryController.setGroup(GROUP);
        remoteRepositoryController.dependsOn(tagController);


        CurrentVersion currentVersion = target.getTasks().create("getCurrentVersion", CurrentVersion.class);
        currentVersion.setGroup(GROUP);
        currentVersion.dependsOn(remoteRepositoryController);


        UncommittedController uncommittedController = target.getTasks()
                .create("isExistUncommittedFiles", UncommittedController.class);
        uncommittedController.setGroup(GROUP);
        uncommittedController.dependsOn(currentVersion);

        ActualVersion actualVersion = target.getTasks().create("getActualVersion", ActualVersion.class);
        actualVersion.setGroup(GROUP);
        actualVersion.dependsOn(uncommittedController);

        Publisher publisher = target.getTasks().create("publish", Publisher.class);
        publisher.setGroup(GROUP);
        publisher.dependsOn(actualVersion);


    }
}