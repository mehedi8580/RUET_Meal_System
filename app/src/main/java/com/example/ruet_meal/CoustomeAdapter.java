package com.example.ruet_meal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CoustomeAdapter extends ArrayAdapter<tokenStore> {

   private Activity context;
   private List<tokenStore> slist;

    public CoustomeAdapter(Activity context, List<tokenStore> slist) {
        super(context,R.layout.sample, slist);
        this.context = context;
        this.slist = slist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample,null,true);
        tokenStore tokenlist=slist.get(position);

        TextView vroll,vlaunch,vdinner;

        vroll = view.findViewById(R.id.troll);
        vlaunch =view.findViewById(R.id.tlaun);
        vdinner=view.findViewById(R.id.tdin);

        vroll.setText(tokenlist.getRoll());
        vlaunch.setText(tokenlist.getLaunch());
        vdinner.setText(tokenlist.getDinner());
        return view;


    }
}
