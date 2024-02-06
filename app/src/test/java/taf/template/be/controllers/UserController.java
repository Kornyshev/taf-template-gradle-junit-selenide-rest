package taf.template.be.controllers;

import io.restassured.http.Method;
import taf.template.be.models.Repository;
import taf.template.be.models.User;
import taf.template.core.ApiClient;

import java.util.List;

public class UserController {

    private static final String GET_USER_DATA = "user";

    public User executeGetUserData() {
        return new ApiClient().build().sendRequest(Method.GET, 200, GET_USER_DATA).as(User.class);
    }

    public List<Repository> executeGetUserRepos() {
        return new ApiClient().build().sendRequest(Method.GET, 200,
                        executeGetUserData().getReposUrl())
                .getBody().jsonPath().getList("", Repository.class);
    }

}
