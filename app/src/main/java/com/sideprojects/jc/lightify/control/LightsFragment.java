package com.sideprojects.jc.lightify.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.apis.philips.hue.LightItem;
import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.view.MarginItemDecoration;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LightsFragment extends Fragment{

    public static final String TAG = LightsFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private LightItemAdapter mAdapter;
    private Subscription mSubFetchLights;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new LightItemAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lights, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_lights);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(
                new MarginItemDecoration(getResources().getDimensionPixelSize(R.dimen.list_item_margin)));
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setItemActionListener(new LightItemAdapter.ItemActionListener() {
            @Override
            public void onItemClicked(LightItem item) {

            }

            @Override
            public void onItemSwitched(LightItem item, boolean isOn) {
                LightItem.RequestBuilder builder = LightItem.RequestBuilder.start()
                        .setOn(isOn)
                        .setTransitionTime(10);
                if(isOn){
                    builder.setBrightness(254);
                }
                PhilipsHueService.lightService().setLightState(item.id(), builder.build())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(responses -> {}, Throwable::printStackTrace);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter.getItems().isEmpty()){
            mSubFetchLights = PhilipsHueService.lightService()
                    .getLights()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(lights -> {
                        mAdapter.setItems(lights);
                        mAdapter.notifyDataSetChanged();
                    }, error -> {});
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mSubFetchLights != null && !mSubFetchLights.isUnsubscribed()){
            mSubFetchLights.unsubscribe();
        }
    }
}
