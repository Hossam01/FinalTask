package com.example.intern.finaltask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.finaltask.MVP.MainView;
import com.example.intern.finaltask.MVP.Presenter;
import com.example.intern.finaltask.Retrofit.GitHubRepo;

import java.util.List;

public class BroadCastTest extends BroadcastReceiver implements MainView.view {


    Presenter mPresenter;
    public BroadCastTest(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        mPresenter=new Presenter(this,context);
        mPresenter.network();
        mPresenter.intentservice();
    }


    @Override
    public void showlist(List<GitHubRepo> gitHubRepos) {

    }

    @Override
    public void showmessage(Context mContext,String message) {
        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
    }
}
