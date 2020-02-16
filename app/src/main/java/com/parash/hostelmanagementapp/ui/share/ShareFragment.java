package com.parash.hostelmanagementapp.ui.share;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.parash.hostelmanagementapp.MainActivity;
import com.parash.hostelmanagementapp.ProfileActivity;
import com.parash.hostelmanagementapp.R;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
//    ImageView imageView;
//    TextView textView;
//    Button deleteButton;
//    TextView username, email;
//    Button btnSignOut,btnLocation,btnExit,btnDeleteUser;
//    Intent intent;
//    DatabaseReference reference;
//    FirebaseUser firebaseUser;
//    int count =0;
//    String fuId="";
//    ProgressBar progressBar;
//    StorageReference storageReference;
//    private Uri imageURl;
//    private StorageTask uploadsTask;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        return root;
//        imageView=root.findViewById(R.id.ProfileImage);
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
//
//        textView=root.findViewById(R.id.profileEmail);
//        textView.setText(firebaseUser.getEmail());
//        deleteButton=root.findViewById(R.id.btnDeleteAccount);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
//                dialog.setTitle("Are you sure?");
//                dialog.setMessage("This will deactivate your account");
//                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful())
//                                {
//                                    Toast.makeText(getContext(), "Account has been deleted", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getContext(), MainActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//
//
//                                }
//                                else
//                                {
//                                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//                    }
//                });
//                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                AlertDialog alertDialog = dialog.create();
//                alertDialog.show();
//            }
//        });
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openImage();
//            }
//        });

    }
//    private void openImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,1);
//    }
//    private String getFileExtension(Uri uri)
//    {
//        Context applicationContext = getActivity().getApplicationContext();
//        applicationContext.getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(applicationContext.getContentResolver().getType(uri));
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if( resultCode == RESULT_OK && data != null )
//        {
//            imageURl =data.getData();
//            uploadImage();
//        }
//
//
//    }
//
//    private void CheckPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//            new AlertDialog.Builder(getContext())
//                    .setTitle("Permission needed")
//                    .setMessage("This permission is needed because of this and that")
//                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(getActivity(),
//                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//                        }
//                    })
//                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .create().show();
//        }
//    }
//    private void uploadImage()
//    {
//
//        if(imageURl !=null)
//        {
//            final  StorageReference fileReference = storageReference.child(System.currentTimeMillis()
//                    +"."+getFileExtension(imageURl));
//            uploadsTask =fileReference.putFile(imageURl);
//            uploadsTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>(){
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if(!task.isSuccessful())
//                    {
//                        throw task.getException();
//                    }
//                    return fileReference.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if(task.isSuccessful())
//                    {
//                        Uri downloadUri = task.getResult();
//                        String mUri = downloadUri.toString();
//
//                        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//                        HashMap<String,Object> map = new HashMap<>();
//                        map.put("image",mUri);
//                        reference.updateChildren(map);
//                    }
//                    else
//                    {
//                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else {
//            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
//        }
//    }

}