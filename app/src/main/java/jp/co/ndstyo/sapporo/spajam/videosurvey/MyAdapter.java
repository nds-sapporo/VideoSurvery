package jp.co.ndstyo.sapporo.spajam.videosurvey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jp.co.ndstyo.sapporo.spajam.videosurvey.backend.User;

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<User> matchingUserList;

    public MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setFoodList(ArrayList<User> foodList) {
        this.matchingUserList = foodList;
    }

    @Override
    public int getCount() {
        return matchingUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return matchingUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.row_matchinguser,parent,false);

        // 社会人としての忖度コーディング
        if( matchingUserList.get(position).getUserName().equals("箱根")) {
            ((ImageView) convertView.findViewById(R.id.imageViewPhoto)).setImageResource(R.drawable.hakone);
        }
        if( matchingUserList.get(position).getUserName().equals("登別")) {
            ((ImageView) convertView.findViewById(R.id.imageViewPhoto)).setImageResource(R.drawable.noboribetsu);
        }
        if( matchingUserList.get(position).getUserName().equals("草津")) {
            ((ImageView) convertView.findViewById(R.id.imageViewPhoto)).setImageResource(R.drawable.kusatsu);
        }

        ((TextView)convertView.findViewById(R.id.name)).setText(matchingUserList.get(position).getUserName() + " さん");

        // 今回は課金額合計までは無理かな
        //((TextView)convertView.findViewById(R.id.price)).setText(String.valueOf(matchingUserList.get(position).getPrice()));

        return convertView;
    }
}
