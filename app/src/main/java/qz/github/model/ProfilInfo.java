package qz.github.model;

import com.google.gson.annotations.SerializedName;

/*
This is Example Json Result Format from Requets
{
  "login": "QiubyZ",
  "id": 149507435,
  "node_id": "U_kgDOCOlNaw",
  "avatar_url": "https://avatars.githubusercontent.com/u/149507435?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/QiubyZ",
  "html_url": "https://github.com/QiubyZ",
  "followers_url": "https://api.github.com/users/QiubyZ/followers",
  "following_url": "https://api.github.com/users/QiubyZ/following{/other_user}",
  "gists_url": "https://api.github.com/users/QiubyZ/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/QiubyZ/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/QiubyZ/subscriptions",
  "organizations_url": "https://api.github.com/users/QiubyZ/orgs",
  "repos_url": "https://api.github.com/users/QiubyZ/repos",
  "events_url": "https://api.github.com/users/QiubyZ/events{/privacy}",
  "received_events_url": "https://api.github.com/users/QiubyZ/received_events",
  "type": "User",
  "site_admin": false,
  "name": null,
  "company": null,
  "blog": "",
  "location": null,
  "email": null,
  "hireable": null,
  "bio": null,
  "twitter_username": null,
  "public_repos": 10,
  "public_gists": 0,
  "followers": 0,
  "following": 0,
  "created_at": "2023-10-31T14:19:14Z",
  "updated_at": "2023-11-24T11:15:03Z"
}
*/

//This is Sample How to create class for take a Json Keys and Values
public class ProfilInfo {
    
    /* 
    @SerializedName("login")
    String username;
    This is for example to take keys "login" value from Json format Result
    And this is will be save automaticly to "username" String Variabel.
    */
    
    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("public_repos")
    private String publicreposCount;

    @SerializedName("followers")
    private String followersCount;

    @SerializedName("created_at")
    private String created_at;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPublicreposCount() {
        return this.publicreposCount;
    }

    public void setPublicreposCount(String publicreposCount) {
        this.publicreposCount = publicreposCount;
    }

    public String getFollowersCount() {
        return this.followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
