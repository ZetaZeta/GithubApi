package com.combiner.githubapi.model;

import java.util.List;

public class UserData {
    public String user_name;
    public String display_name;
    public String avatar;
    public String geo_location;
    public String email;
    public String url;
    public String created_at;
    public List<UserRepo> repos;

    public UserData(String username){
        this.user_name=username;
    }

    /**
     * Takes values from the given GitHub User Response and sets the corresponding fields in this object.
     * @param userResponse A GitHub user response whose fields should be used to set values.
     */
    public void setUserData(GithubUserResponse userResponse) {
        display_name = userResponse.name;
        avatar = userResponse.avatar_url;
        geo_location = userResponse.location;
        email = userResponse.email;
        url = userResponse.url;
        created_at = userResponse.created_at.toString();
    }

    /**
     * Sets this object's list of user repos to the ones from the given list from GitHub's API.
     * @param githubRepos A list of GitHub repos from GitHub's api.
     */
    public void setRepos(List<GithubRepoResponse> githubRepos) {
        repos = githubRepos.stream().map(UserRepo::new).toList();
    }
}

