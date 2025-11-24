package com.combiner.githubapi.model;

public class UserRepo {
    UserRepo(GithubRepoResponse githubRepoResponse) {
        name = githubRepoResponse.name;
        url = githubRepoResponse.url;
    }

    public String name;
    public String url;
}
