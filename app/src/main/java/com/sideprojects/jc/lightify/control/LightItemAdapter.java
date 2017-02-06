package com.sideprojects.jc.lightify.control;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sideprojects.jc.lightify.apis.philips.hue.LightItem;
import com.sideprojects.jc.lightify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LightItemAdapter extends RecyclerView.Adapter<LightItemAdapter.ItemHolder> {

    private List<LightItem> mItems;

    public LightItemAdapter(){
        mItems = new ArrayList<>();
    }

    public void setItems(List<LightItem> items){
        mItems.clear();
        mItems.addAll(items);
    }

    public List<LightItem> getItems(){
        return mItems;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_light, null);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        if(position < 0 || position >= mItems.size()){
            return;
        }
        LightItem item = mItems.get(position);
        holder.title.setText(item.name());
        holder.switchButton.setChecked(item.state().isOn());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private SwitchCompat switchButton;

        public ItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textview_title);
            switchButton = (SwitchCompat) itemView.findViewById(R.id.switch_button);
        }
    }
}
