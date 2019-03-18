package com.hisense.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProvinceAdapter extends BaseAdapter {

    private List<ProvinceBean> provinceList;
    private LayoutInflater layoutInflater;

    public ProvinceAdapter(Context context, List<ProvinceBean> provinceList) {
        this.provinceList = provinceList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return provinceList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.province_grid_view_item_layout, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProvinceBean provinceBean = provinceList.get(position);
        if (provinceBean != null) {
            holder.text.setText(provinceBean.getName());
            holder.text.setBackgroundResource(provinceBean.getColor());
        }
        return convertView;
    }

    class ViewHolder {
        TextView text;
    }
}
