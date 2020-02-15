package com.parash.hostelmanagementapp.ui.home;

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
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parash.hostelmanagementapp.Adapter.StudentAdapter;
import com.parash.hostelmanagementapp.Model.Student;
import com.parash.hostelmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= view.findViewById(R.id.studentListRecycleView);
        studentList= new ArrayList<>();
        loadStudent();

        return view;
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

                        studentList.add(student);


                }
                studentAdapter= new StudentAdapter(getContext(),studentList);
                recyclerView.setAdapter(studentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}