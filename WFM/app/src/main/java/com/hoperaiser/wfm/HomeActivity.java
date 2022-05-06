package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,SelectListner {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView i1;
    Toolbar toolbar;
    FloatingActionButton Add_data;
    EditText Food_Quantity_editttext,food_description_edittext;
    TimePicker time_edittext;
    Spinner food_type_edittext;
    Button Add_food_data;
    String retrive_name,retrive_hotel_name,retrive_email,retrive_phone_no,retrive_alt_no,retrive_Address,fq,fd,timelimit;
    String rv_retrive_name,rv_retrive_hotel_name,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_Address,rv_fq,rv_fd,rv_timelimit,rv_food_quantity,rv_food_description,rv_food_type,iA,iV,iAva;
    String isApproved="0";
    String isVisible="0";
    String isAvailable="0";
    String spinner_foot_type;
    ArrayAdapter<String> dataAdapter;
    TextView main_name;
    private RecyclerView rv;
    private List<Dataclass>list_data;
    private MyAdapter adapter;
    String current;

    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference,ref;
    String uid;
    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        i1 = findViewById(R.id.menu);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        Add_data=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        main_name=(TextView)findViewById(R.id.textView3);

        rv=(RecyclerView)findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
        adapter=new MyAdapter(list_data,this,this);

        navigationView.bringToFront();

        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) HomeActivity.this);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)this);
        navigationView.setCheckedItem(R.id.home);
        i1.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat. START));


        //Firebase
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("User");
        ref = root.getReference("Data");
        uid=mAuth.getCurrentUser().getUid();



        //Logout
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mAuth.signOut();
//
//
////                mAuth.signOut().addOnCompleteListener( new OnCompleteListener<Void>() {
////                    @Override
////                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(HomeActivity.this, "Logout out", Toast.LENGTH_SHORT).show();
//                        Intent i=new Intent(HomeActivity.this,LoginActivity.class);
//                        startActivity(i);
//                        finish();
////                    }
////                });
//
//
//            }
//        });


        //Reterive data


        Query query = reference.orderByChild("uid").equalTo(uid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    Dataclass person = ds.getValue(Dataclass.class);

                    retrive_name=person.getName();
                    retrive_email=person.getEmail();
                    retrive_hotel_name=person.getHotel_name();
                    retrive_phone_no=person.getPhone_no();
                    retrive_alt_no=person.getAlt_no();
                    retrive_Address=person.getAddress();

                     main_name.setText(retrive_name);


                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Reterive data


        reference = root.getReference("Data");
        Query query1 = reference.orderByChild("uid").equalTo(uid);

        query1.addValueEventListener(new ValueEventListener() {
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


                    // list data
                    Dataclass u = new Dataclass(uid,rv_retrive_name,rv_retrive_hotel_name, rv_food_type,rv_food_quantity,rv_food_description,current,rv_retrive_email,rv_retrive_phone_no,rv_retrive_alt_no,rv_retrive_Address,rv_timelimit,isApproved,isVisible,isAvailable);
                    list_data.add(person);

                }

                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Add Data

        Add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialogBuilder=new AlertDialog.Builder(HomeActivity.this).create();
                LayoutInflater inflater=HomeActivity.this.getLayoutInflater();
                View dialogview=inflater.inflate(R.layout.hotel_add_data,null);

                //Reterive data


                Query query = reference.orderByChild("uid").equalTo(uid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot ds : snapshot.getChildren()) {

                            Dataclass person = ds.getValue(Dataclass.class);

                            retrive_name=person.getName();
                            retrive_email=person.getEmail();
                            retrive_hotel_name=person.getHotel_name();
                            String retrive_phone_no1=person.getPhone_no();
                            retrive_alt_no=person.getAlt_no();
                            retrive_Address=person.getAddress();

                            main_name.setText(retrive_name);


                        }


                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                Food_Quantity_editttext =(EditText) dialogview.findViewById(R.id.food_Quantity);
                food_description_edittext=(EditText)dialogview.findViewById(R.id.food_description);
                time_edittext=(TimePicker)dialogview.findViewById(R.id.editTextTime);
                food_type_edittext=(Spinner)dialogview.findViewById(R.id.food_type);
                Add_food_data=(Button)dialogview.findViewById(R.id.update);

                timelimit=String.valueOf(time_edittext.getHour()+":"+time_edittext.getMinute());


                //Spinner year

                List<String> year_spinner=new ArrayList<>();
                year_spinner.add(0,"Food Type");
                year_spinner.add("VEG");
                year_spinner.add("NON-VEG");
                dataAdapter=new ArrayAdapter(HomeActivity.this,android.R.layout.simple_spinner_item,year_spinner);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                food_type_edittext.setAdapter(dataAdapter);
                food_type_edittext.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner_foot_type=food_type_edittext.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                // Add food data

                reference = root.getReference("Data");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        maxid =(snapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Add_food_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SimpleDateFormat s=new SimpleDateFormat("HH:mm:ss");
                        current=s.format(new Date());
                        Toast.makeText(HomeActivity.this, current, Toast.LENGTH_SHORT).show();
                        Dataclass u = new Dataclass(uid,retrive_name,retrive_hotel_name, spinner_foot_type, String.valueOf(Food_Quantity_editttext.getText()), String.valueOf(food_description_edittext.getText()),current,retrive_email,retrive_phone_no,retrive_alt_no,retrive_Address,timelimit,isApproved,isVisible,isAvailable);
//                        reference.child(String.valueOf(maxid)).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                             reference.child(current).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent i=new Intent(HomeActivity.this,HomeActivity.class);
                                    startActivity(i);

                                    Toast.makeText(HomeActivity.this, " Data Added ", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                });



                dialogBuilder.setView(dialogview);
                dialogBuilder.show();

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                break;
            case R.id.about:

                Intent i2=new Intent(getApplicationContext(),hotel_historyActivity.class);
                startActivity(i2);
                break;
            case R.id.profile_hotel:

                Intent i3=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(i3);
                break;

            case R.id.feedback:
                Intent call=new Intent(Intent.ACTION_DIAL);
                String phoneno="9751487543";
                call.setData(Uri.parse("tel:"+ phoneno));
                Toast.makeText(HomeActivity.this, "call", Toast.LENGTH_SHORT).show();
                startActivity(call);
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

    @Override
    public void onItemClicked(Register userModal) {

    }
}