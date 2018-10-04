package com.example.intern.finaltask.MVP;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ListView;

import com.example.intern.finaltask.DataManager;
import com.example.intern.finaltask.Database;
import com.example.intern.finaltask.Pass;
import com.example.intern.finaltask.Retrofit.GitHubRepo;
import com.example.intern.finaltask.ServiceTest;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class Presenter implements MainView.presenter,Pass {
    Pass mPass;

    Context mContext;
    DataManager mDataManager;
    ListView mListView;

    MainView.view mMainView;
    RealmList<Database> mDatabaseRealmList = new RealmList<>();


    public Presenter(MainView.view view)
    {
        mMainView=view;
    }

    public Presenter( MainView.view mainView,Context context)
    {mContext=context;
    mMainView=mainView;}
    public Presenter(DataManager dataManager,Context context)
    {
        mDataManager=dataManager;
        mContext=context;
    }
    @Override
    public void network() {

        String message;
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            message= "true";
        }
        else {
            message= "false";
        }

        mMainView.showmessage(mContext,message);
    }

    @Override
    public void intentservice() {


        ServiceTest.startActionBaz(mContext,this);

       /* ServiceTest serviceTest = new ServiceTest(mContext,this);
        mContext.startService(new Intent(mContext,serviceTest.getClass()));*/
    }


    @Override
    public void sendData(List<GitHubRepo> gitHubRepo) {

        mMainView.showlist(gitHubRepo);

        Realm.init(mContext);

        Realm realm = Realm.getDefaultInstance();

        for (int i=0;i<=gitHubRepo.size()-1;i++) {
            Database database = new Database();
            database.setId(gitHubRepo.get(i).getId());
            database.setName(gitHubRepo.get(i).getName());
            mDatabaseRealmList.add(database);
        }

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mDatabaseRealmList);
        realm.commitTransaction();
    }

}
