package com.sideprojects.jc.lightify.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sideprojects.jc.lightify.R;
import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.apis.philips.hue.messeging.HueBridge;
import com.sideprojects.jc.lightify.view.MarginItemDecoration;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by justin.chu on 2/5/17.
 */
public class BridgeDiscoveryFragment extends Fragment {

    public static final String TAG = BridgeDiscoveryFragment.class.getSimpleName();

    private static final String TAG_DISCOVERED_BRIDGES = "discovered_bridges";

    private TextView mTextViewStatus;
    private RecyclerView mRecyclerViewBridges;
    private BridgeItemAdapter mAdapter;
    private Subscription mSubDiscovery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new BridgeItemAdapter();
        if(savedInstanceState != null){
            if(savedInstanceState.containsKey(TAG_DISCOVERED_BRIDGES)){
                mAdapter.setItems(savedInstanceState.getParcelableArrayList(TAG_DISCOVERED_BRIDGES));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bridge_discovery, container, false);
        mTextViewStatus = (TextView) root.findViewById(R.id.textview_status);
        mRecyclerViewBridges = (RecyclerView) root.findViewById(R.id.recyclerview_bridges);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerViewBridges.setLayoutManager(
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerViewBridges.addItemDecoration(
                new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerViewBridges.addItemDecoration(
                new MarginItemDecoration(getResources().getDimensionPixelSize(R.dimen.list_item_margin)));
        mRecyclerViewBridges.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mAdapter.setItemClickListener(bridge -> ((DiscoveryActivity) getActivity()).startOnboarding(bridge));
    }

    @Override
    public void onStart() {
        super.onStart();
        mSubDiscovery = PhilipsHueService.discoveryService().discoverBridges()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> mTextViewStatus.setText(getString(R.string.discovering_bridges)))
                .doOnNext(bridges -> mTextViewStatus.setText(getString(R.string.discovered_bridges)))
                .doOnError(error -> mTextViewStatus.setText(getString(R.string.discovery_bridges_error)))
                .subscribe(bridges -> {
                    mAdapter.setItems(bridges);
                    mAdapter.notifyDataSetChanged();
                }, error -> {
                    Toast.makeText(getContext(), getString(R.string.discovery_bridges_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error occurred while discovering : " + error.toString());
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mSubDiscovery != null && !mSubDiscovery.isUnsubscribed()){
            mSubDiscovery.unsubscribe();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mAdapter.getItems().isEmpty()){
            outState.putParcelableArrayList(TAG_DISCOVERED_BRIDGES,
                    (ArrayList<HueBridge>) mAdapter.getItems());
        }
    }
}
