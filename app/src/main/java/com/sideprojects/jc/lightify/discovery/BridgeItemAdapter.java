package com.sideprojects.jc.lightify.discovery;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sideprojects.jc.lightify.apis.philips.hue.PhilipsHueService;
import com.sideprojects.jc.lightify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin.chu on 2/5/17.
 */

public class BridgeItemAdapter extends RecyclerView.Adapter<BridgeItemAdapter.ItemHolder> {

    public interface ItemClickListener {
        void onBridgeItemClicked(PhilipsHueService.Bridge bridge);
    }

    private @NonNull List<PhilipsHueService.Bridge> mItems;
    private ItemClickListener mListener;

    public BridgeItemAdapter(){
        mItems = new ArrayList<>();
    }

    public void setItems(List<PhilipsHueService.Bridge> items){
        mItems.clear();
        if(items != null) {
            mItems.addAll(items);
        }
    }

    public @NonNull List<PhilipsHueService.Bridge> getItems(){
        return mItems;
    }

    public void setItemClickListener(ItemClickListener listener){
        mListener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_bridge, null);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if(position < 0 || position >= mItems.size()){
            return;
        }
        Resources res = holder.itemView.getResources();
        PhilipsHueService.Bridge bridge = mItems.get(position);
        holder.nameView.setText(bridge.name() != null ? bridge.name() : res.getString(R.string.no_bridge_name));
        holder.macView.setText(bridge.mac());
        holder.ipView.setText(bridge.ip());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public RelativeLayout rootView;
        public TextView nameView;
        public TextView macView;
        public TextView ipView;

        public ItemHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.rootview);
            nameView = (TextView) itemView.findViewById(R.id.textview_name);
            macView = (TextView) itemView.findViewById(R.id.textview_mac);
            ipView = (TextView) itemView.findViewById(R.id.textview_ip);
            Button button = (Button) itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(getAdapterPosition() > -1 && getAdapterPosition() < mItems.size()){
                mListener.onBridgeItemClicked(mItems.get(getAdapterPosition()));
            }
        }
    }
}
