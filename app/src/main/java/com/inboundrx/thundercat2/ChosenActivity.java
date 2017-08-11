package com.inboundrx.thundercat2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import com.inboundrx.thundercat2.BeaconIds.BeaconUid;
import java.util.List;
import java.util.UUID;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ChosenActivity extends AppCompatActivity {
    private BeaconManager beaconManager;
    private Region region;
    public CountDownTimer rewardTimer;
    private boolean timerExists;
    public long timeRemaining;
    @Bind(R.id.textField1) TextView mTextField1;
    @Bind(R.id.textField2) TextView mTextField2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen);
        ButterKnife.bind(this);
        timerExists = false;
        timeRemaining = 600000;
        mTextField1.setVisibility(View.GONE);
        mTextField2.setVisibility(View.GONE);
        if (getIntent().hasExtra("reward")){
            mTextField1.setVisibility(View.VISIBLE);
            mTextField2.setVisibility(View.VISIBLE);
            mTextField1.setText("time until reward: ");
            mTextField2.setText(" " + timeRemaining / 1000 + " ");
        }
        if (getIntent().hasExtra("map")){

        }
        if (getIntent().hasExtra("user")){

        }
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Log.d("logging range", list.get(0).toString());
                    if (!timerExists){
                        durationReward(timerExists);
                        timerExists = true;
                    }
                    Log.d("logging range", BeaconUid.BEACON_UUID);
                    Log.d("Demo App: ", BeaconUid.BEACON_UUID);
                } else {
                    if (timerExists){
                        durationReward(timerExists);
                        timerExists = false;
                    }
                }
            }
        });
        region = new Region("ranged region", UUID.fromString(BeaconUid.BEACON_UUID), null, null);
    }

    public void durationReward(boolean switcher) {
        if (!switcher){
           rewardTimer = new CountDownTimer(timeRemaining, 1000) {
                public void onTick(long millisUntilFinished) {
                    mTextField1.setText("time until reward: ");
                    mTextField2.setText(" " + millisUntilFinished / 1000 + " ");
                    timeRemaining = millisUntilFinished;
                }
                public void onFinish() {
                    mTextField2.setText("done!");
                }
            }.start();
        } else {
            rewardTimer.cancel();
        }
    }

    @Override
    protected void onPause(){
        beaconManager.stopRanging(region);
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady(){
                beaconManager.startRanging(region);
            }
        });
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}
