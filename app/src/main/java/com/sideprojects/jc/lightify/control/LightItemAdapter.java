package com.sideprojects.jc.lightify.control;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sideprojects.jc.lightify.apis.philips.hue.LightItem;
import com.sideprojects.jc.lightify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LightItemAdapter extends RecyclerView.Adapter<LightItemAdapter.ItemHolder> {

    public interface ItemActionListener{
        void onItemClicked(LightItem item);
        void onItemSwitched(LightItem item, boolean isOn);
    }

    private List<LightItem> mItems;
    private ItemActionListener mListener;

    public LightItemAdapter(){
        mItems = new ArrayList<>();
    }

    public void setItems(List<LightItem> items){
        mItems.clear();
        mItems.addAll(items);
    }

    public void setItemActionListener(ItemActionListener listener){
        mListener = listener;
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

    public class ItemHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        private TextView title;
        private SwitchCompat switchButton;

        public ItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textview_title);
            switchButton = (SwitchCompat) itemView.findViewById(R.id.switch_button);
            switchButton.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(getAdapterPosition() > -1 && getAdapterPosition() < mItems.size()){
                // Only notify when user actually clicks on the switch.
                if(buttonView.isPressed()) {
                    mListener.onItemSwitched(mItems.get(getAdapterPosition()), isChecked);
                }
            }
        }
    }
}
