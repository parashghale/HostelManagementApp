package com.parash.hostelmanagementapp.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parash.hostelmanagementapp.R;

import java.util.HashMap;

public class GalleryFragment extends Fragment {

    Context context;

    private GalleryViewModel galleryViewModel;

    String roomRent ="";

    EditText fullaname, email,phonenumber, locaiton;
    CheckBox rent;
    Button btnSaveStudent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        String [] values =
                {"Single person room","Two person room","Three person room","Four person room"};
        final Spinner spinner = (Spinner) root.findViewById(R.id.roomSpinner);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
        fullaname=root.findViewById(R.id.etFullname);
        email=root.findViewById(R.id.etEmail);
        phonenumber=root.findViewById(R.id.etPhoneNumber);
        locaiton=root.findViewById(R.id.etLocation);
        btnSaveStudent=root.findViewById(R.id.btnAddStudent);
        rent=root.findViewById(R.id.rent);
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = fullaname.getText().toString();
                String Email= email.getText().toString();
                String phone = phonenumber.getText().toString();
                String Location = locaiton.getText().toString();
                String room = spinner.getSelectedItem().toString();
                if(TextUtils.isEmpty(name))
                {
                    fullaname.setError("Please Enter First Name");
                    fullaname.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(Email))
                {
                    email.setError("Enter First Name");
                    email.requestFocus();
                    return;
                }     if(TextUtils.isEmpty(phone))
                {
                    phonenumber.setError("Please Enter First Name");
                    phonenumber.requestFocus();
                    return;
                }     if(TextUtils.isEmpty(Location))
                {
                    locaiton.setError("Please Enter First Name");
                    locaiton.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(room))
                {
                    Toast.makeText(getContext(), "Select a room", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    if(rent.isChecked())
                    {
                           roomRent="paid";
                    }
                    else
                    {
                        roomRent="notpaid";
                    }
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("id",reference.push().getKey());
                    hashMap.put("fullname",name);
                    hashMap.put("email",Email);
                    hashMap.put("phone",phone);
                    hashMap.put("location",Location);
                    hashMap.put("room",room);
                    hashMap.put("roomRent",roomRent);
                    reference.child("Student").push().setValue(hashMap);
                    Toast.makeText(getContext(), "Student added sucessfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }
}