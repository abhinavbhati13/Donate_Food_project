package com.example.food_donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<>();
    User currentUser;
    EditText name,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.textName);
        password = findViewById(R.id.textPass);


        Button buttonOne = findViewById(R.id.loginbtn);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Clicked");
                if(validateUser())
                {
                    System.out.println("Current User type "+ currentUser.type);
                    if(currentUser.type.equals("Restaurant"))
                    {
                        Intent activity2Intent = new Intent(getApplicationContext(), DonateFood.class);
                        startActivity(activity2Intent);
                    }
                    else if(currentUser.type.equals("NGO"))
                    {
                        Intent activity2Intent = new Intent(getApplicationContext(), NGOPage.class);
                        startActivity(activity2Intent);
                    }
                    else if(currentUser.type.equals("Delivery Man"))
                    {
                        Intent activity2Intent = new Intent(getApplicationContext(), AvailableDelivery.class);
                        startActivity(activity2Intent);
                    }

                }
                else
                {
                    System.out.println("");
                    //Toast.makeText(MainActivity.this, "Wrong Credentials, Please Try Again!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Button buttonTwo = findViewById(R.id.signup);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button 2 Clicked");
                    Intent activity2Intent = new Intent(getApplicationContext(), Registration.class);
                    startActivity(activity2Intent);
            }
        });
    }

    public boolean validateUser()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://food-donate-new-default-rtdb.firebaseio.com/");
        System.out.println("Got connection ");
        DatabaseReference userRef = database.getReference("User");
        System.out.println("Users ..");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot usersnap : dataSnapshot.getChildren())
                {
                    System.out.println("dataSnapshot ..");
                    User user = usersnap.getValue(User.class);
                    userList.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        System.out.println("Abhi userList "+ userList);
        for(User u : userList)
        {
            System.out.println("User name "+u.name);
            System.out.println("User password"+u.password);
            System.out.println("name is "+ name.getText().toString());
            System.out.println("password is "+password.getText().toString());
            if(name.getText().toString().equals(u.name) && password.getText().toString().equals(u.password))
            {
                currentUser = u;
                return true;
            }

        }
        return false;
    }
}