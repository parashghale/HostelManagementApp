package com.parash.hostelmanagementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parash.hostelmanagementapp.Model.Student;
import com.parash.hostelmanagementapp.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList =studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_adapter,parent,false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Student student = studentList.get(position);
        holder.name.setText(student.getFullname());
        holder.location.setText(student.getLoction());
        holder.phone.setText(student.getPhone());
        holder.room.setText(student.getRoom());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,phone,location,room;
        public Button btndelete,btnEdit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.studentName);
            phone=itemView.findViewById(R.id.studentPhoneNumber);
            location=itemView.findViewById(R.id.studentLocation);
            room=itemView.findViewById(R.id.studentRoom);
            btndelete=itemView.findViewById(R.id.btndeletestudent);
            btnEdit=itemView.findViewById(R.id.btneditStudnet);

        }
    }
}
