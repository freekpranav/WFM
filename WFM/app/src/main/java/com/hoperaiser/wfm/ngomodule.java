package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ngomodule extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SelectListner{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private RecyclerView rv;
    private List<Dataclass> list_data;
    private ngoadapter adapter;
    String rv_retrive_name,rv_retrive_hotel_name,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_Address,rv_fq,rv_fd,rv_timelimit,rv_food_quantity,rv_food_description,rv_food_type,iA,iV,iAva,current;
    ImageView i1;


    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference,ref;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngomodule);
        i1 = findViewById(R.id.menu);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.bringToFront();

        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) ngomodule.this);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)this);
        navigationView.setCheckedItem(R.id.home);
        i1.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat. START));


        rv=(RecyclerView)findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
        adapter=new ngoadapter(list_data,this,this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(getApplicationContext(),SearchActivity.class);
//                startActivity(i);
                break;
            case R.id.about:
                Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();

                Intent i2=new Intent(getApplicationContext(),ngoviewActivity.class);
                startActivity(i2);
                break;

            case R.id.profile_hotel:
//
                Intent i3=new Intent(getApplicationContext(),NgoprofileActivity.class);
                startActivity(i3);

                break;

            case R.id.share:
                Intent i1=new Intent(Intent.ACTION_SEND);
                i1.setType("text/plain");
                String body="Hi, I invite you to check out the awesome WFM app , ** Play Store App Url **";
                i1.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(i1,"Share via"));
                break;



        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}