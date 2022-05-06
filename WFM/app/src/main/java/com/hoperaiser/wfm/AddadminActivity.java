package com.hoperaiser.wfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddadminActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    Button add;
    FloatingActionButton profileback;
    //Firebase
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);
        profileback=(FloatingActionButton)findViewById(R.id.profileback);
        name=findViewById(R.id.Name);
        phone=findViewById(R.id.phone_no);
        add=findViewById(R.id.update);
        pd = new ProgressDialog(this);
        pd.setMessage("Registering....");
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("Admin");

        profileback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
try{
    String getname=name.getText().toString();
    String getpassword=phone.getText().toString();
    mAuth.createUserWithEmailAndPassword(getname, getpassword)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        AddAdmin u=new AddAdmin(getname,getpassword);
                        reference.child(getname).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    pd.dismiss();
                                    Toast.makeText(AddadminActivity.this, "Admin Added Succesfully", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


                    } else {
                        pd.dismiss();

                        Toast.makeText(AddadminActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
}catch(Exception e){

}
            }
        });
    }
}