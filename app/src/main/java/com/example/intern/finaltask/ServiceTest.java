package com.example.intern.finaltask;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.intern.finaltask.MVP.MainView;
import com.example.intern.finaltask.MVP.Presenter;
import com.example.intern.finaltask.Retrofit.GitHubClient;
import com.example.intern.finaltask.Retrofit.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceTest extends IntentService {

    static Pass mPass;
    Context mContext;

    Realm realm;
    Presenter mPresenter;
    String BASE_URL = "https://api.github.com/";


    public ServiceTest(){
        super("test");
    }

    public Intent startService(Context mContext, Pass mPass){
        Intent intent = new Intent(mContext,ServiceTest.class);
        this.mContext = mContext;
        this.mPass = mPass;
        return intent;
    }

    public ServiceTest(Context mContext,Pass mPass) {
        super("test");
        this.mContext = mContext;
        this.mPass = mPass;
    }



    public static void startActionBaz(Context context, Pass mpass) {
        Intent intent = new Intent(context, ServiceTest.class);
        mPass=mpass;
        context.startService(intent);
        Log.d("startBaz",Thread.currentThread().getName());
    }
    @Override
    public void onCreate() {
        super.onCreate();
        final Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubClient client=retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call=client.reposForUser("hossam01");
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repoList = response.body();
                Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
                mPass.sendData(repoList);


            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

                List<GitHubRepo>   repoList = new ArrayList<>();
                Realm.init(getApplicationContext());
                realm = Realm.getDefaultInstance();
                RealmResults<Database> puppies = realm.where(Database.class).findAll();

                for(Database model:puppies) {
                    repoList.add(new GitHubRepo(model.getId(), model.getName()));
                    mPass.sendData(repoList);
                }
            }
        });

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this,"start",Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"destroy",Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {




    }


}
