package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
TextView register_btn;
    TextInputEditText register_edittext,password_edittext;
    Button loginbtn;
    TextView registertext,forgot_password;
    ProgressDialog pd;

    String uid;

    //Firebase

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    FirebaseDatabase rootnode;
    DatabaseReference reference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_edittext=(TextInputEditText)findViewById(R.id.registerno_login);
        password_edittext=(TextInputEditText)findViewById(R.id.password_login);
        loginbtn=(Button)findViewById(R.id.loginbtn_login);
        forgot_password=(TextView)findViewById(R.id.forgot_password);
        pd = new ProgressDialog(this);
        pd.setMessage("Verifying....");
        register_btn=(TextView)findViewById(R.id.Register_text);


        //Firebase
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mAuth=FirebaseAuth.getInstance();
        uid=mAuth.getUid();

        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("User");




        register_btn.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String re=register_edittext.getText().toString();
                String pass=password_edittext.getText().toString();

                if(re.matches("")&& pass.matches("")) {

                    Toast.makeText(LoginActivity.this, " Kindly fill All the field to login", Toast.LENGTH_SHORT).show();
                }else {
                    pd.show();

                    mAuth.signInWithEmailAndPassword(re, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {




                                        // fetch data & display



                                        Query query = reference.orderByChild("uid").equalTo(uid);
                                        query.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                for (DataSnapshot ds : snapshot.getChildren()) {

                                                    Register  person = ds.getValue(Register.class);

                                                    String retrivename = person.getApp_type();

                                               if(retrivename.equals("ORGANIZATION")){
                                                     pd.dismiss();
                                                     Toast.makeText(LoginActivity.this, "organization", Toast.LENGTH_SHORT).show();

                                                     Intent i = new Intent(LoginActivity.this, ngomodule.class);
                                                     startActivity(i);
                                                 }
                                                    if(retrivename.equals("HOTEL")){

                                                        pd.dismiss();
                                                        Toast.makeText(LoginActivity.this, "Hotel", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                                        startActivity(i);

                                                    }
//                                                 else
                                                 if(retrivename.equals("ADMIN")){
                                                     pd.dismiss();
                                                     Toast.makeText(LoginActivity.this, "ADMIN", Toast.LENGTH_SHORT).show();

                                                     Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                                                     startActivity(i);
                                                 }

                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });







                                    } else {
                                        pd.dismiss();

                                        Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }


            }


        });

    }
}