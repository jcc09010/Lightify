package com.sideprojects.jc.lightify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.control.ControlActivity;
import com.sideprojects.jc.lightify.discovery.DiscoveryActivity;
import com.sideprojects.jc.lightify.utils.PreferencesUtil;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        String bridgeIP = PreferencesUtil.getBridgeIP(this);
        String userId = PreferencesUtil.getUserId(this);
        if(bridgeIP == null || userId== null) {
            startDiscovery();
        } else {
            PhilipsHueService.setupSharedLightService(bridgeIP, userId);
            startControl();
        }
    }

    private void startDiscovery(){
        Intent discovery = new Intent(this, DiscoveryActivity.class);
        startActivity(discovery);
        finish();
    }

    private void startControl(){
        Intent control = new Intent(this, ControlActivity.class);
        startActivity(control);
        finish();
    }
}
