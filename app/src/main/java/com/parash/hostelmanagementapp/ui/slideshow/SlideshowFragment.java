package com.parash.hostelmanagementapp.ui.slideshow;

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

import com.parash.hostelmanagementapp.Adapter.RoomAdapter;
import com.parash.hostelmanagementapp.Adapter.StudentAdapter;
import com.parash.hostelmanagementapp.Model.Room;
import com.parash.hostelmanagementapp.Model.Student;
import com.parash.hostelmanagementapp.R;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView recyclerView;
    private List<Room> roomList;
    private RoomAdapter roomAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerView=root.findViewById(R.id.roomRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        roomList = new ArrayList<>();
        roomList.add(new Room("Platinum room","Single person room","15000",R.drawable.single));
        roomList.add(new Room("Premium room","Two person room","12000",R.drawable.two));
        roomList.add(new Room("Deluxe room","Three person room","10000",R.drawable.three));
        roomList.add(new Room("Normal room","Four person room","8000",R.drawable.four));
        roomAdapter= new RoomAdapter(getContext(),roomList);
        recyclerView.setAdapter(roomAdapter);
        return root;
    }
}