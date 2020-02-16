package com.parash.hostelmanagementapp.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parash.hostelmanagementapp.Adapter.PaymentAdapter;
import com.parash.hostelmanagementapp.Adapter.StudentAdapter;
import com.parash.hostelmanagementapp.Model.Student;
import com.parash.hostelmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private RecyclerView recyclerView;
    private List<Student> studentList;
    private PaymentAdapter paymentAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        recyclerView= root.findViewById(R.id.paymentRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        studentList= new ArrayList<>();
        loadStudent();
        return root;
    }
    private void loadStudent()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for(DataSnapshot snapshot :  dataSnapshot.getChildren())
                {
                    Student student = snapshot.getValue(Student.class);
                    if(student.getRoomRent().equals("notpaid")||student.getRoomRent().equals(""))
                    {
                        studentList.add(student);
                    }


                }
                paymentAdapter= new PaymentAdapter(getContext(),studentList);
                recyclerView.setAdapter(paymentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}