package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView owner_name,hotel_name,email,phoneno,alt_phone,address;
    ImageView edit_profile;
    ImageView profile;
    Button Logout_profile;
    FloatingActionButton homeback;

    //Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase rootnode;
    DatabaseReference reference,ref;
    String uid;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile=(ImageView)findViewById(R.id.profile_image);
        owner_name=(TextView)findViewById(R.id.Account_name);
        hotel_name=(TextView)findViewById(R.id.DOB);
        email=(TextView)findViewById(R.id.Student_email);
        phoneno=(TextView)findViewById(R.id.blood_group);
        address=(TextView)findViewById(R.id.address);
     alt_phone=(TextView)findViewById(R.id.students_phone);
        Logout_profile=(Button)findViewById(R.id.Logout_profile);
        homeback=(FloatingActionButton)findViewById(R.id.homeback);
        //Floating button

        edit_profile=(ImageView)findViewById(R.id.edit_profile);

        //Firebase
        mAuth=FirebaseAuth.getInstance();
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("User");
        uid=mAuth.getCurrentUser().getUid();


        // Back

        homeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        // fetch data & display



        Query query = reference.orderByChild("uid").equalTo(uid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    updateprofile  person = ds.getValue(updateprofile.class);

                    String retrivename = person.getName();
                    String retrivehot_name = person.getHot_org_name();
                    String retrive_address = person.getAddress();
                    String retrive_email = person.getEmail();
                    String retrive_phoneno = person.getPhone();
                    String retrive_alt_no = person.getAlt_no();

                    String student_address=person.getAddress();

                    owner_name.setText(retrivename);
                    hotel_name.setText(retrivehot_name);
                    email.setText(retrive_email);
                    phoneno.setText(retrive_phoneno);
                    alt_phone.setText(retrive_alt_no);

                    address.setText(student_address);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Profile edit


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProfileActivity.this,Profile_edit_Activity.class));





            }
        });
        // Logout


        Logout_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAuth.signOut();
                Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);




            }
        });

    }
}