package com.parash.hostelmanagementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parash.hostelmanagementapp.Model.Student;
import com.parash.hostelmanagementapp.R;

import java.util.HashMap;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>{
    private Context context;
    private List<Student> studentList;


    public PaymentAdapter(Context context, List<Student> Student) {
        this.context = context;
        this.studentList =Student;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_adapter,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Student student = studentList.get(position);
        holder.name.setText(student.getFullname());
        if(student.getRoom()=="Single person room")
        {
            holder.price.setText("15000");
        }
        else  if(student.getRoom()=="Two person room")
        {
            holder.price.setText("12000");
        }
        else  if(student.getRoom()=="Three person room")
        {
            holder.price.setText("10000");
        }
        else
        {
            holder.price.setText("8000");
        }

        if(holder.checkBox.isChecked())
        {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student").child(student.getId());
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("roomRent","paid");
            reference.updateChildren(hashMap);
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,price;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.paymentStudentName);
            price=itemView.findViewById(R.id.paymentPrice);
            checkBox=itemView.findViewById(R.id.paymentRoomRent);



        }
    }
}
