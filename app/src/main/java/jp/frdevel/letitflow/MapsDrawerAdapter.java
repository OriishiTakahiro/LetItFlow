package jp.frdevel.letitflow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MapsDrawerAdapter extends ArrayAdapter<MapsDrawerItem> {

    private Context mContext;
    private List<MapsDrawerItem> mDrawerItemList;
    private int mLayoutResID;

    public MapsDrawerAdapter(
            Context context, int layoutResID, List<MapsDrawerItem> itemList) {
        super(context, layoutResID, itemList);
        mContext = context;
        mDrawerItemList = itemList;
        mLayoutResID = layoutResID;
    }

    @Override
    @SuppressWarnings("deprecation")
    public View getView(int pos, View convertView, ViewGroup parent) {
        MapsDrawerItemHolder drawerHolder;
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            drawerHolder = new MapsDrawerItemHolder();

            view = inflater.inflate(mLayoutResID, parent, false);
            drawerHolder.mItemName = (TextView) view.findViewById(R.id.drawer_item_name);
            drawerHolder.mIcon = (ImageView) view.findViewById(R.id.drawer_item_icon);

            view.setTag(drawerHolder);
        } else {
            drawerHolder = (MapsDrawerItemHolder) view.getTag();
        }

        MapsDrawerItem item = this.mDrawerItemList.get(pos);
        drawerHolder.mIcon.setImageResource(item.mImgResID);
        drawerHolder.mItemName.setText(item.mItemName);
        return view;
    }

    private static class MapsDrawerItemHolder {
        public TextView mItemName;
        public ImageView mIcon;
    }
}
