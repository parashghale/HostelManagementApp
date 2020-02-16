package com.parash.hostelmanagementapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.parash.hostelmanagementapp.Model.User;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Button deleteButton;
    TextView username, email;
    Button btnSignOut,btnLocation,btnExit,btnDeleteUser;
    Intent intent;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    int count =0;
    String fuId="";
    ProgressBar progressBar;
    StorageReference storageReference;
    private Uri imageURl;
    private StorageTask uploadsTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        textView.setText(firebaseUser.getEmail());
        deleteButton=findViewById(R.id.btnDeleteAccount);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==1)
                {
                    firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Account has been deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);


                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Press again to quit.", Toast.LENGTH_SHORT).show();
                    count++;
                }


            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                textView.setText(user.getUsename());

                if(user.getImage().equals("noimage"))
                {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                else
                {
                    Glide.with(getApplicationContext()).load(user.getImage()).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

    }
    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == RESULT_OK && data != null )
        {
            imageURl =data.getData();
            uploadImage();
        }


    }

    private void CheckPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(getApplicationContext())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ProfileActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
    }
    private void uploadImage()
    {
        progressBar.setVisibility(View.VISIBLE);
        if(imageURl !=null)
        {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageURl));
            uploadsTask =fileReference.putFile(imageURl);
            uploadsTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>(){
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("image",mUri);
                        reference.updateChildren(map);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                        vibrator.vibrate(500);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "No image selected", Toast.LENGTH_SHORT).show();
            Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(500);
        }
    }

}
