package com.apps.idhamrahadian.hitsradioapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.apps.idhamrahadian.hitsradioapps.R;
import com.apps.idhamrahadian.hitsradioapps.model.ProgramModel;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.MyHolder> {
    List<ProgramModel> mList ;
    Context ctx;

    public ProgramAdapter(Context ctx, List<ProgramModel> mList) {
        this.mList = mList;
        this.ctx = ctx;
    }




    @Override
    public ProgramAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;

    }

    @Override
    public void onBindViewHolder(ProgramAdapter.MyHolder holder, final int position) {
        ProgramModel superheroModel = mList.get(position);
        holder.prog_name.setText(superheroModel.getProg_name());
        holder.prog_desc.setText(superheroModel.getProg_desc());
        holder.prog_hours.setText(superheroModel.getProg_hours());
        holder.prog_day.setText(superheroModel.getProg_day());


    }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView prog_name, prog_desc, prog_hours, prog_day;
        ProgramModel ProgramModel;
        public MyHolder(View v)
        {
            super(v);

            prog_name  = (TextView) v.findViewById(R.id.txtProgName);
            prog_desc = (TextView) v.findViewById(R.id.txtProgDesc);
            prog_hours = (TextView) v.findViewById(R.id.txtProgHours);
            prog_day = (TextView) v.findViewById(R.id.txtProgDay);


        }

    }
}