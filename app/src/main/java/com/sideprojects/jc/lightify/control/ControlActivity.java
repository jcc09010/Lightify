package com.sideprojects.jc.lightify.control;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.apis.philips.hue.data.LightItem;

/**
 * Created by justin.chu on 2/5/17.
 */

public class ControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        if(savedInstanceState == null){
            showLightLists();
        }
    }

    public void showLightLists(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, LightsFragment.newInstance(), LightsFragment.TAG)
                .commit();
    }

    public void showLightControl(@NonNull LightItem item){
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_frame, LightControlFragment.newInstance(item), LightControlFragment.TAG)
                .commit();
    }
}
