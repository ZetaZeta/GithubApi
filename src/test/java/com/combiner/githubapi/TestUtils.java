package com.combiner.githubapi;

public class TestUtils {
    public static String getOctocatJson() {
        return """
  {
  "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
  "created_at": "Tue Jan 25 13:44:36 EST 2011",
  "display_name": "The Octocat",
  "email": null,
  "geo_location": "San Francisco",
  "repos": [
    {
      "name": "boysenberry-repo-1",
      "url": "https://api.github.com/repos/octocat/boysenberry-repo-1"
    },
    {
      "name": "git-consortium",
      "url": "https://api.github.com/repos/octocat/git-consortium"
    },
    {
      "name": "hello-worId",
      "url": "https://api.github.com/repos/octocat/hello-worId"
    },
    {
      "name": "Hello-World",
      "url": "https://api.github.com/repos/octocat/Hello-World"
    },
    {
      "name": "linguist",
      "url": "https://api.github.com/repos/octocat/linguist"
    },
    {
      "name": "octocat.github.io",
      "url": "https://api.github.com/repos/octocat/octocat.github.io"
    },
    {
      "name": "Spoon-Knife",
      "url": "https://api.github.com/repos/octocat/Spoon-Knife"
    },
    {
      "name": "test-repo1",
      "url": "https://api.github.com/repos/octocat/test-repo1"
    }
  ],
  "url": "https://api.github.com/users/octocat",
  "user_name": "octocat"
}""";
    }
}
