package com.example.intern.finaltask;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.intern.finaltask.MVP.MainView;
import com.example.intern.finaltask.MVP.Presenter;
import com.example.intern.finaltask.Retrofit.GitHubRepo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity implements MainView.view{


    List<GitHubRepo> mGitHubRepoList;
    ListView mListView;
    RealmList<Database> mDatabaseRealmList = new RealmList<>();
    Realm realm;
    private Pass mPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView=findViewById(R.id.list_item);

        Presenter mPresenter=new Presenter(this,getApplicationContext());
        mPresenter.intentservice();


       // mListView.setAdapter(new Adapter(getApplicationContext(), R.layout.item, mGitHubRepoList));

    }


    @Override
    public void showlist(List<GitHubRepo> gitHubRepos) {
        mListView.setAdapter(new Adapter(getApplicationContext(),R.layout.item,gitHubRepos));
    }



    @Override
    public void showmessage(Context context, String message) {
    }



}
