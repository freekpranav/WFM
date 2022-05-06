package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Admin_Details_Activity extends AppCompatActivity {
    TextView hotel_name,name,food_quantity,food_type,food_description,endtime,Address,phoneno,email;
    String hotel_name_rv,name_rv,food_quantity_rv,food_type_rv,food_description_rv,endtime_rv,Address_rv,phoneno_rv,email_rv,uid,Current_time;
    Button verify,decline;

    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference,ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__details_);

        hotel_name=(TextView)findViewById(R.id.hotel_name);
        name=(TextView)findViewById(R.id.label6);
        food_quantity=(TextView)findViewById(R.id.label9);
        food_type=(TextView)findViewById(R.id.label10);
        food_description=(TextView)findViewById(R.id.label11);
        endtime=(TextView)findViewById(R.id.label12);
        Address=(TextView)findViewById(R.id.Addressdetails);
        phoneno=(TextView)findViewById(R.id.Phone);
        email=(TextView)findViewById(R.id.label8);
        hotel_name=(TextView)findViewById(R.id.hotel_name);
        verify=(Button)findViewById(R.id.verified_hotel_btn);
        decline=(Button)findViewById(R.id.decline_hotel_btn);
        Intent intent=getIntent();
        name_rv=intent.getExtras().getString("name");
        hotel_name_rv=intent.getExtras().getString("hotelname");
        food_quantity_rv=intent.getExtras().getString("fq");
        food_type_rv=intent.getExtras().getString("ft");
        food_description_rv=intent.getExtras().getString("fd");
        endtime_rv=intent.getExtras().getString("end_time");
        Address_rv=intent.getExtras().getString("address");
        phoneno_rv=intent.getExtras().getString("altno");
        email_rv=intent.getExtras().getString("mail");
        uid=intent.getExtras().getString("uid");
        Current_time=intent.getExtras().getString("current");


        hotel_name.setText(hotel_name_rv);
        name.setText(name_rv);
        food_quantity.setText(food_quantity_rv);
        food_type.setText(food_type_rv);
        food_description.setText(food_description_rv);
        endtime.setText(endtime_rv);
        Address.setText(Address_rv);
        phoneno.setText(phoneno_rv);
        email.setText(email_rv);
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("User");
        ref = root.getReference("Data");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass u = new Dataclass(uid,name_rv,hotel_name_rv, food_type_rv, food_quantity_rv,food_description_rv,Current_time,email_rv,phoneno_rv,phoneno_rv,Address_rv,endtime_rv,"1","0","0");
                ref.child(Current_time).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(getApplicationContext(),AdminActivity.class);
                            startActivity(i);
                            Toast.makeText(Admin_Details_Activity.this, " Hotel verified ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin_Details_Activity.this, "decline", Toast.LENGTH_SHORT).show();
            }
        });



    }
}