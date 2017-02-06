package com.sideprojects.jc.lightify.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sideprojects.jc.lightify.R;

/**
 * Created by justin.chu on 2/5/17.
 */

public class OnboardingFragment extends Fragment {

    public static final String TAG = OnboardingFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.fragment_onboarding, null);
    }
}
