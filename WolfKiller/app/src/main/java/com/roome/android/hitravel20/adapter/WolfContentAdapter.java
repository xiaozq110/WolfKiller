package com.roome.android.hitravel20.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.roome.android.hitravel20.R;

/**
 * Created by Administrator on 2017/4/25.
 */

public class WolfContentAdapter extends BaseAdapter {
    private String mContent = "";

    private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局

    public WolfContentAdapter(Context context) {
        Log.i("TAG","WolfContentAdapter");
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setGodContent(String content){
        Log.i("TAG","adapter===content="+mContent);
        this.mContent = content;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_godcontent, null);
            holder = new ViewHolder();
            holder.tv_content = (TextView) convertView.findViewById(R.id.item_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i("TAG","adapter===content="+mContent);
        holder.tv_content .setText(mContent);
        return convertView;
    }
    public final class ViewHolder {
        public TextView tv_content;

    }
}
