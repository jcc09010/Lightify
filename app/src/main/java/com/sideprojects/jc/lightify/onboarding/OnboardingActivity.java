package com.sideprojects.jc.lightify.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.control.ControlActivity;
import com.sideprojects.jc.lightify.preferences.PreferencesUtil;

import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by justin.chu on 2/5/17.
 */

public class OnboardingActivity extends AppCompatActivity {

    public static final String TAG = OnboardingActivity.class.getSimpleName();

    private PhilipsHueService.Bridge mBridge;
    private Subscription mSubOnboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        if(getIntent().hasExtra(PhilipsHueService.Bridge.TAG)){
            mBridge = getIntent().getParcelableExtra(PhilipsHueService.Bridge.TAG);
        }
        showPressHuePrompt();
    }

    private void showPressHuePrompt(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new PressHuePromptFragment(), PressHuePromptFragment.TAG)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .commit();
    }

    private void showOnboardingFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new OnboardingFragment(), OnboardingFragment.TAG)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .commit();
    }

    private void showSuccessFragment(@NonNull PhilipsHueService.Bridge bridge, @NonNull String userId){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, OnboardSuccessFragment.newInstance(bridge, userId), OnboardSuccessFragment.TAG)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .commit();
    }

    public void startControl(){
        Intent control = new Intent(this, ControlActivity.class);
        startActivity(control);
        finish();
    }

    public void startOnboarding(){
        String uid = "testUser";
        mSubOnboard = PhilipsHueService.onboardBridge(mBridge, uid)
                .timeout(15, TimeUnit.SECONDS)
                /**
                 * For some reason, Philips Hue Bridge returns a list of onboarding responses
                 * for each onboarding action. We only need the first response here.
                 */
                .map(responses -> responses.get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::showOnboardingFragment)
                .subscribe(response -> {
                    if(response.success() != null){
                        /**
                         * Onboard Success, Save the bridge IP and user ID and
                         * setup Philips Hue Light Control Service.
                         */
                        String bridgeIp = mBridge.ip();
                        String userId = response.success().userId();
                        PreferencesUtil.setBridgeIp(this, bridgeIp);
                        PreferencesUtil.setUserId(this, userId);
                        PhilipsHueService.setupSharedLightService(mBridge.ip(), userId);
                        showSuccessFragment(mBridge, userId);
                        Log.e(TAG, "Hue Bridge Onboarded");
                    } else if (response.error() != null){
                        /**
                         * Onboard Failed, Return to onboard prompt screen and
                         * show error message.
                         */
                        showPressHuePrompt();
                        Toast.makeText(this, response.error().errorMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error occurred while onboarding : " + response.error().errorMessage());
                    }
                }, error -> {
                    /**
                     * Onboard Failed, Return to onboard prompt screen and
                     * show error message.
                     */
                    showPressHuePrompt();
                    Toast.makeText(this, getString(R.string.onboarding_error_toast), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error occurred while onboarding : " + error.toString());
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mSubOnboard != null && !mSubOnboard.isUnsubscribed()){
            mSubOnboard.unsubscribe();
        }
    }
}
