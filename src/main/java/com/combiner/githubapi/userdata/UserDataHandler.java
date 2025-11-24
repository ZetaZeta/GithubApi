package com.combiner.githubapi.userdata;

import com.combiner.githubapi.client.GithubClient;
import com.combiner.githubapi.model.GithubRepoResponse;
import com.combiner.githubapi.model.GithubUserResponse;
import com.combiner.githubapi.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class UserDataHandler {
    private final GithubClient githubClient;

    @Autowired
    public UserDataHandler(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    /**
     * Fetches and combines the GitHub user data and repo data for the given username.
     * @param username The username whose data we're retrieving
     * @return The combined user data object.
     */
    @Cacheable("usernames")
    public Mono<UserData> getUserData(String username) {
        UserData userData = new UserData(username);

        Mono<GithubUserResponse> userMono = githubClient.getUser(username);
        Mono<List<GithubRepoResponse>> repoMono = githubClient.getRepos(username);
        return Mono.zip(repoMono, userMono, (r, u) -> {
           userData.setUserData(u);
           userData.setRepos(r);
           return userData;
        });
    }
}
