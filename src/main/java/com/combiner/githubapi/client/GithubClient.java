package com.combiner.githubapi.client;

import com.combiner.githubapi.model.GithubRepoResponse;
import com.combiner.githubapi.model.GithubUserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

import reactor.core.publisher.Mono;

@Component
public class GithubClient {
    static WebClient webClient = WebClient.create("https://api.github.com");

    /**
     * Gets the user information for the given Github username
     * @see <a href="https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user">...</a>
     *
     * @param username A valid GitHub username
     * @return A mono that will get user information for the given GitHub username when run.
     */
    public Mono<GithubUserResponse> getUser(String username){
        return getFromGithub("/users/" + username)
                .bodyToMono(GithubUserResponse.class);
    }

    /**
     * Gets a list of repos for the given GitHub username.
     * @see <a href="https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-the-authenticated-user">...</a>
     *
     * @param username A valid GitHub username
     * @return A mono that will get repos for the given GitHub username when run.
     */
    public Mono<List<GithubRepoResponse>> getRepos(String username){
        return getFromGithub("/users/" + username + "/repos")
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    /**
     * Puts together a basic webclient configuration to get the given URI from GitHub.
     * @see <a href="https://docs.github.com/en/rest/using-the-rest-api/getting-started-with-the-rest-api?apiVersion=2022-11-28">...</a>
     *
     * @param uri The URI at GitHub to retrieve.
     * @return A ResponseSpec, which should be turned into a mono or flux object to run.
     */
    private WebClient.ResponseSpec getFromGithub(String uri) {
        return  webClient.get()
                .uri(uri)
                .header("Accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .retrieve();
    }
}
