package com.sideprojects.jc.lightify.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.apis.philips.hue.messeging.HueBridge;
import com.sideprojects.jc.lightify.utils.PreferencesUtil;

/**
 * Created by justin.chu on 2/5/17.
 */

public class OnboardSuccessFragment extends Fragment {

    public static final String TAG = OnboardSuccessFragment.class.getSimpleName();

    public static OnboardSuccessFragment newInstance(@NonNull HueBridge bridge,
                                                     @NonNull String userId) {
        Bundle args = new Bundle();
        args.putParcelable(HueBridge.TAG, bridge);
        args.putString(PreferencesUtil.PREFERENCE_USER_ID, userId);
        OnboardSuccessFragment fragment = new OnboardSuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboard_success, container, false);
        TextView bridgeInfoView = (TextView) view.findViewById(R.id.textview_bridge_info);
        TextView userIdView = (TextView) view.findViewById(R.id.textview_user_id);
        Button startControlButton = (Button) view.findViewById(R.id.button_start_control);

        if(getArguments().containsKey(HueBridge.TAG)){
            HueBridge bridge = getArguments().getParcelable(HueBridge.TAG);
            bridgeInfoView.setText(String.format(getString(R.string.bridge_info),
                    bridge.name(),
                    bridge.mac(),
                    bridge.ip()));
        }
        if(getArguments().containsKey(PreferencesUtil.PREFERENCE_USER_ID)){
            String userId = getArguments().getString(PreferencesUtil.PREFERENCE_USER_ID);
            userIdView.setText(String.format(getString(R.string.user_id_info), userId));
        }
        startControlButton.setOnClickListener(buttonView -> ((OnboardingActivity) getActivity()).startControl());
        return view;
    }
}
