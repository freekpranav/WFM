package com.hoperaiser.wfm;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Dataclass> list_data;
    private Context context;
    List<Dataclass> newlist;
    SelectListner listner;
    public MyAdapter(List<Dataclass> list_data, Context context,SelectListner listner) {
        this.list_data=list_data;
        this.context = context;
        this.newlist=list_data;
        this.listner=listner;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dataclass listData=list_data.get(position);
        holder.retrive_hotel_name.setText(listData.getName());
        holder.retrive_phone_no.setText(listData.getAlt_no());
        holder.food_type.setText(listData.getFood_type());
        holder.count.setText(listData.getFood_Quantity());
        holder.maincardview.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_down));
        holder.maincardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onItemClicked(listData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView retrive_name,retrive_hotel_name,retrive_phone_no,count,food_type;

        private CardView maincardview;
        public ViewHolder(View itemView) {
            super(itemView);
            retrive_hotel_name=(TextView)itemView.findViewById(R.id.idTVFirstName);
            retrive_phone_no=(TextView)itemView.findViewById(R.id.idTVLastName);
            count=(TextView)itemView.findViewById(R.id.idTVEmail);
            food_type=(TextView)itemView.findViewById(R.id.food_type);
            maincardview=(CardView)itemView.findViewById(R.id.maincardview);

        }
    }

}