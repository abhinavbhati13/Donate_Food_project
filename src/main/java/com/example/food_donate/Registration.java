package com.example.food_donate;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton type;
    EditText name,location,mobile,password;
    long max_id = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        radioGroup = findViewById(R.id.radioGroup);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        password = findViewById(R.id.password);
        mobile = findViewById(R.id.mobile);
        type = findViewById(radioGroup.getCheckedRadioButtonId());
    }
    public void checkButton(View v)
    {
        type = findViewById(radioGroup.getCheckedRadioButtonId());
    }
    public void addRecord(View view)
    {
        System.out.println("Adding record res of database ");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://food-donate-new-default-rtdb.firebaseio.com/");
        System.out.println("Got connection ");
        DatabaseReference userRef = database.getReference().child("User");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    max_id = max_id + (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        User user = new User(String.valueOf(max_id+1),name.getText().toString(),type.getText().toString(),mobile.getText().toString(),location.getText().toString(),password.getText().toString());
        userRef.push().setValue(user);
        name.setText("");
        type.setText("");
        mobile.setText("");
        location.setText("");
        password.setText("");
        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
    }

}