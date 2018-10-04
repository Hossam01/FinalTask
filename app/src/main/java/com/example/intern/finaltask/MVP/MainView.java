package com.example.intern.finaltask.MVP;

import android.content.Context;
import android.widget.ListView;

import com.example.intern.finaltask.Retrofit.GitHubRepo;

import java.util.List;

public interface MainView {

    interface presenter{

        void network();
        void intentservice();
    }
    interface view{
        void showlist( List<GitHubRepo> gitHubRepos);
        void showmessage(Context context, String message);

    }
}
