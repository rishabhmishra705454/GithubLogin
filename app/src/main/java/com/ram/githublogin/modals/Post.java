package com.ram.githublogin.modals;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    int id;
    @SerializedName("stargazers_count")
    int stargazers_count;
    @SerializedName("open_issues")
    int open_issues;
    @SerializedName("name")
    String name;
    @SerializedName("pushed_at")
    String pushed_at;

    public Post(int id, int stargazers_count, int open_issues, String name, String pushed_at) {
        this.id = id;
        this.stargazers_count = stargazers_count;
        this.open_issues = open_issues;
        this.name = name;
        this.pushed_at = pushed_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }
}
