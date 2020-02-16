package com.parash.hostelmanagementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parash.hostelmanagementapp.Model.Room;
import com.parash.hostelmanagementapp.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{
    private Context context;
    private List<Room> roomList;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList =roomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_adapter,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Room room = roomList.get(position);
        holder.name.setText(room.getName());
        holder.price.setText("Rs "+room.getPrice());
        holder.type.setText(room.getType());
        holder.image.setImageResource(room.getImage());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,price,type;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.roomName);
            price =itemView.findViewById(R.id.roomPrice);
            type =itemView.findViewById(R.id.roomtype);
            image=itemView.findViewById(R.id.roomImage);


        }
    }
}
