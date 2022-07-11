package com.example.food_donate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    RadioGroup radioGroup;
    RadioButton foodtype;
    EditText foodName,restaurantName;
    String amountValue,foodtypeValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        foodName = (EditText) findViewById(R.id.foodName);
        restaurantName = (EditText) findViewById(R.id.restaurantName);
        Spinner amount = (Spinner) findViewById(R.id.amount);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddFood.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.food_quantity));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amount.setAdapter(myAdapter);
        amount.setOnItemSelectedListener(this);
        Spinner foodtype = (Spinner) findViewById(R.id.foodtype);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddFood.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.food_type));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodtype.setAdapter(myAdapter1);
        foodtype.setOnItemSelectedListener(this);
    }

    public void addFoodRecord(View view)
    {
        System.out.println("Adding record res of database ");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://food-donate-new-default-rtdb.firebaseio.com/");
        System.out.println("Got connection ");
        System.out.println("amount value ***"+amountValue);
        System.out.println("food type value ***"+foodtypeValue);
        DatabaseReference foodref = database.getReference().child("FoodDetails");
        FoodDetail fd = new FoodDetail(foodName.getText().toString(),foodtypeValue,restaurantName.getText().toString(),amountValue);
        foodref.push().setValue(fd);
        foodName.setText("");
        restaurantName.setText("");
        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()== R.id.amount)
        {
            amountValue = parent.getItemAtPosition(position).toString();
            System.out.println("amount value ***"+amountValue);
        }
        if(parent.getId()==R.id.foodtype)
        {
            foodtypeValue = parent.getItemAtPosition(position).toString();
            System.out.println("food type value ***"+foodtypeValue);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}