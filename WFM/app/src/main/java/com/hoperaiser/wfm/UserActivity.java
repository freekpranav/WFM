package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {



    private RecyclerView rv;
    private List<Register> list_data;
    private useradmin adapter;
    String rv_retrive_name,rv_retrive_hotel_name,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_Address,rv_fq,rv_fd,rv_timelimit,rv_food_quantity,rv_food_description,rv_food_type,iA,iV,iAva,current;
    String rv_retrive_app_type;
    FloatingActionButton homeback;


    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference,ref;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        homeback=(FloatingActionButton)findViewById(R.id.homeback);
        rv=(RecyclerView)findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<Register>();
        adapter=new useradmin(list_data,this);
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("User");
        ref = root.getReference("Data");
        uid=mAuth.getCurrentUser().getUid();


        //back

        homeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddadminActivity.class);
                startActivity(i);
            }
        });

        //Reterive data
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Register person = ds.getValue(Register.class);


                    rv_retrive_name=person.getName();
                    rv_retrive_email=person.getEmail();
                    rv_retrive_hotel_name=person.getHot_org_name();
                    rv_retrive_phone_no=person.getPhone();
                    rv_retrive_alt_no=person.getAlt_no();
                    rv_retrive_Address=person.getAddress();
                    rv_retrive_app_type=person.getApp_type();
                    // list data
                    Register u = new Register(uid,rv_retrive_name,rv_retrive_app_type,rv_retrive_Address,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_hotel_name,"passsword");
                    list_data.add(person);
                }

                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}