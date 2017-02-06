package com.sideprojects.jc.lightify.discovery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.onboarding.OnboardingActivity;

/**
 * Created by justin.chu on 2/5/17.
 */

public class DiscoveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
    }

    public void startOnboarding(PhilipsHueService.Bridge bridge){
        Intent intent = new Intent(this, OnboardingActivity.class);
        intent.putExtra(PhilipsHueService.Bridge.TAG, bridge);
        startActivity(intent);
        finish();
    }
}
