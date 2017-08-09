package com.inboundrx.thundercat2;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mapDemoButton) Button mMapDemoButton;
    @Bind(R.id.rewardDemoButton) Button mRewardDemoButton;
    @Bind(R.id.userDemoButton) Button mUserDemoButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ButterKnife.bind(this);
        mMapDemoButton.setOnClickListener(this);
        mRewardDemoButton.setOnClickListener(this);
        mUserDemoButton.setOnClickListener(this);
        openAnimation1();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        mAuth.signOut();
        Intent intent = new Intent(SelectionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void openAnimation4(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mUserDemoButton.animate().translationY(0).setDuration(1200);

            }
        }, 300);
    }

    private void openAnimation3(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRewardDemoButton.animate().translationY(0).setDuration(1200);
                openAnimation4();
            }
        }, 300);
    }

    private void openAnimation2(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMapDemoButton.animate().translationY(0).setDuration(1200);
                openAnimation3();
            }
        }, 300);
    }

    private void openAnimation1(){
        mMapDemoButton.animate().translationY(3000).translationX(0).setDuration(1);
        mRewardDemoButton.animate().translationY(3000).translationX(0).setDuration(1);
        mUserDemoButton.animate().translationY(3000).translationX(0).setDuration(1);
        openAnimation2();
    }
    @Override
    public void onResume(){
        super.onResume();
        openAnimation1();
    }

    @Override
    public void onClick(View v){
        if (v == mMapDemoButton){
            mMapDemoButton.animate().translationX(3000).setDuration(600);
            mRewardDemoButton.animate().translationX(-3000).setDuration(600);
            mUserDemoButton.animate().translationX(-3000).setDuration(600);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //ToDo: update UI
                }
            }, 600);
        }
        if (v == mRewardDemoButton){
            mMapDemoButton.animate().translationX(-3000).setDuration(600);
            mRewardDemoButton.animate().translationX(3000).setDuration(600);
            mUserDemoButton.animate().translationX(-3000).setDuration(600);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //ToDo: update UI
                }
            }, 600);
        }
        if (v == mUserDemoButton){
            mMapDemoButton.animate().translationX(-3000).setDuration(600);
            mRewardDemoButton.animate().translationX(-3000).setDuration(600);
            mUserDemoButton.animate().translationX(3000).setDuration(600);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //ToDo: update UI
                }
            }, 600);
        }
    }
}
