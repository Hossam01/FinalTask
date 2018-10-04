package com.example.intern.finaltask;

import com.example.intern.finaltask.Retrofit.GitHubRepo;

public class DataManager {

    GitHubRepo mGitHubRepo;
    public DataManager(GitHubRepo mGitHubRepo) {
        this.mGitHubRepo = mGitHubRepo;
    }
    public void setid(int id){
        mGitHubRepo.setId(id);
    }
    public void setname(String name){
        mGitHubRepo.setName(name);
    }

}
