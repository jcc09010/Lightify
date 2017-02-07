package com.sideprojects.jc.lightify.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.apis.philips.hue.data.LightItem;
import com.sideprojects.jc.lightify.utils.NormalizationUtil;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */

public class LightControlFragment extends Fragment {

    public static final String TAG = LightControlFragment.class.getSimpleName();

    public static LightControlFragment newInstance(LightItem item) {
        Bundle args = new Bundle();
        args.putParcelable(LightItem.TAG, item);
        LightControlFragment fragment = new LightControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView mTitle;
    private SwitchCompat mSwitch;
    private SeekBar mSeekbarBrightness;
    private SeekBar mSeekbarHue;
    private SeekBar mSeekbarSaturation;
    private SeekBar mSeekbarColorTemp;

    private LightItem mItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(LightItem.TAG)){
            mItem = getArguments().getParcelable(LightItem.TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_light_control, container, false);
        mTitle = (TextView) root.findViewById(R.id.title);
        mSwitch = (SwitchCompat) root.findViewById(R.id.light_switch);
        mSeekbarBrightness = (SeekBar) root.findViewById(R.id.seekbar_brightness);
        mSeekbarHue = (SeekBar) root.findViewById(R.id.seekbar_hue);
        mSeekbarSaturation = (SeekBar) root.findViewById(R.id.seekbar_saturation);
        mSeekbarColorTemp = (SeekBar) root.findViewById(R.id.seekbar_color_temp);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mItem != null){
            update(mItem);
        }
    }

    private void update(LightItem item){
        if (item == null || item.state() == null){
            return;
        }
        mTitle.setText(item.name());
        mSwitch.setChecked(item.state().isOn());
        mSeekbarBrightness.setProgress(NormalizationUtil.normalizeBrightness(
                item.state().brightness()));
        mSeekbarHue.setProgress(NormalizationUtil.normalizeHue(
                item.state().hue()));
        mSeekbarSaturation.setProgress(NormalizationUtil.normalizeSaturation(
                item.state().saturation()));
        mSeekbarColorTemp.setProgress(NormalizationUtil.normalizeColorTemp(
                item.state().colorTemperature()));
    }
}
