package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile_edit_Activity extends AppCompatActivity {

    //Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase rootnode;
    DatabaseReference reference,ref;
    String uid;
    EditText Name,hotel_name,Email,Phoneno,Alt_Phoneno,Address;
    Button update;
    FloatingActionButton profileback;
    ProgressDialog pd;
    String retrive_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_);

        Name =(EditText)  findViewById(R.id.Name);
        hotel_name =(EditText)  findViewById(R.id.hotel_name);
        Email=(EditText) findViewById(R.id.Email);
        Phoneno=(EditText) findViewById(R.id.phone_no);
        Alt_Phoneno=(EditText) findViewById(R.id.alt_Phone_no);
        Address=(EditText) findViewById(R.id.Address);
        update=(Button) findViewById(R.id.update);
        profileback=(FloatingActionButton)findViewById(R.id.profileback);


        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");


        mAuth=FirebaseAuth.getInstance();
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("User");
        uid=mAuth.getCurrentUser().getUid();
        //back
        profileback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
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

                    Register  person = ds.getValue(Register.class);

                    String retrivename = person.getName();
                    String retrivehot_name = person.getHot_org_name();
                    String retrive_address = person.getAddress();
                    String retrive_email = person.getEmail();
                    String retrive_phoneno = person.getPhone();
                    String retrive_alt_no = person.getAlt_no();
                    retrive_password=person.getPassword();

                    String student_address=person.getAddress();

                    Name.setText(retrivename);
                    hotel_name.setText(retrivehot_name);
                    Email.setText(retrive_email);
                    Phoneno.setText(retrive_phoneno);
                    Alt_Phoneno.setText(retrive_alt_no);

                    Address.setText(student_address);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rn=Name.getText().toString();
                String rhn=hotel_name.getText().toString();
                String re=Email.getText().toString();
                String rpn=Phoneno.getText().toString();
                String rapn=Alt_Phoneno.getText().toString();
                String ra=Address.getText().toString();

                Register u=new Register(uid,rn,"HOTEL",ra,re,rpn,rapn,rhn,retrive_password);
                reference.child(uid).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(Profile_edit_Activity.this, " Updated Succesfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}