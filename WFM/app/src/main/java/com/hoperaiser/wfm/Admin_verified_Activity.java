package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin_verified_Activity extends AppCompatActivity implements SelectListner {

    private RecyclerView rv;
    private List<Dataclass> list_data;
    private hotelhistory adapter;
    String rv_retrive_name,rv_retrive_hotel_name,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_Address,rv_fq,rv_fd,rv_timelimit,rv_food_quantity,rv_food_description,rv_food_type,iA,iV,iAva,current;



    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference,ref;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verified_);
        rv=(RecyclerView)findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
        adapter=new hotelhistory(list_data,this,this);
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("User");
        ref = root.getReference("Data");
        uid=mAuth.getCurrentUser().getUid();

        //Reterive data
        Query query = ref.orderByChild("isApproved").equalTo("1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Dataclass person = ds.getValue(Dataclass.class);


                    rv_retrive_name=person.getName();
                    rv_retrive_email=person.getEmail();
                    rv_retrive_hotel_name=person.getHotel_name();
                    rv_retrive_phone_no=person.getPhone_no();
                    rv_retrive_alt_no=person.getAlt_no();
                    rv_retrive_Address=person.getAddress();
                    rv_food_description=person.getFood_Description();
                    rv_food_quantity=person.getFood_Quantity();
                    rv_food_type=person.getFood_type();
                    rv_timelimit=person.getTime_limit();
                    iA=person.getIsApproved();
                    iV=person.getIsVisible();
                    iAva=person.getIsAvailable();
                    current=person.getCurrent_time();
                    // list data
                    Dataclass u = new Dataclass(uid,rv_retrive_name,rv_retrive_hotel_name, rv_food_type,rv_food_quantity,rv_food_description,current,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_Address,rv_timelimit,iA,iV,iAva);
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

    @Override
    public void onItemClicked(Dataclass userModal) {

    }

    @Override
    public void onItemClicked(Register userModal) {

    }
}