package taf.template.be.controllers;

import io.restassured.http.Method;
import taf.template.be.models.Repository;
import taf.template.core.ApiClient;

public class RepositoriesController {

    private static final String DELETE_REPO_BY_NAME = "repos/{owner}/{repo}";
    private static final String POST_NEW_REPO = "/user/repos";

    public void executeDeleteRepoForUserByRepoName(String userName, String repository) {
        new ApiClient().build().sendRequest(Method.DELETE, 204,
                DELETE_REPO_BY_NAME, userName, repository);
    }

    public void executePostNewRepo(Repository repository) {
        new ApiClient().build().addJsonContentType()
                .addHeader("Accept", "application/vnd.github+json")
                .addBody(repository)
                .sendRequest(Method.POST, 201, POST_NEW_REPO);
    }

}
