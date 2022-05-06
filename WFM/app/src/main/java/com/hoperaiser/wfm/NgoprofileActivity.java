package com.hoperaiser.wfm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class NgoprofileActivity extends AppCompatActivity {
    ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoprofile);
        edit=findViewById(R.id.edit_profile);
        Intent i1=new Intent(getApplicationContext(),NgoeditActivity.class);
        startActivity(i1);

    }
}