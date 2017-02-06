package com.sideprojects.jc.lightify.onboarding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sideprojects.jc.lightify.R;

/**
 * Created by justin.chu on 2/5/17.
 */

public class PressHuePromptFragment extends Fragment {

    public static final String TAG = PressHuePromptFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_press_hue_prompt, container, false);
        Button onboardButton = (Button) view.findViewById(R.id.button_onboard);
        onboardButton.setOnClickListener(buttonView -> ((OnboardingActivity) getActivity()).startOnboarding());
        return view;
    }

}
