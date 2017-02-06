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

import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.R;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LightsFragment extends Fragment{

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
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
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
