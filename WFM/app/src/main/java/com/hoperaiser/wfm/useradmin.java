package com.hoperaiser.wfm;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class useradmin extends RecyclerView.Adapter<useradmin.ViewHolder> {
    List<Register> list_data;
    private Context context;
    List<Register> newlist;

    public useradmin(List<Register> list_data, Context context) {
        this.list_data=list_data;
        this.context = context;
        this.newlist=list_data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Register listData=list_data.get(position);
        holder.retrive_hotel_name.setText(listData.getName());
        holder.type.setText(listData.getApp_type());

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView retrive_name,retrive_hotel_name,retrive_phone_no,count,food_type,admin_hotel_verify,ngo_accept,type;

        private CardView maincardview;
        public ViewHolder(View itemView) {
            super(itemView);
            retrive_hotel_name=(TextView)itemView.findViewById(R.id.idTVFirstName);
            type=(TextView)itemView.findViewById(R.id.hot_org_name);
            maincardview=(CardView)itemView.findViewById(R.id.maincardview);

        }
    }

}