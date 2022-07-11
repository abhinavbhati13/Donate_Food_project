package com.example.food_donate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchFood extends AppCompatActivity {
    List<FoodDetail> foodDetailList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);
        showfood();
    }

    public boolean showfood()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://food-donate-new-default-rtdb.firebaseio.com/");
        System.out.println("Got connection ");
        DatabaseReference userRef = database.getReference("FoodDetails");
        System.out.println("foodDetails ..");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodDetailList.clear();
                for(DataSnapshot usersnap : dataSnapshot.getChildren())
                {
                    System.out.println("dataSnapshot ..");
                    FoodDetail food_detail = usersnap.getValue(FoodDetail.class);
                    foodDetailList.add(food_detail);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        TableLayout tbl = (TableLayout) findViewById(R.id.fooddetail);
        System.out.println("Abhinav foodDetailList "+ foodDetailList);
        RadioGroup rg = new RadioGroup(this);
        for(FoodDetail fd : foodDetailList)
        {
            TableRow tr0 = new TableRow(this);

            TextView tv0 = new TextView(this);
            tv0.setText(fd.foodName);
            tv0.setWidth(1);
            tv0.setGravity(Gravity.CENTER);

            tr0.addView(tv0);

            TextView tv1 = new TextView(this);
            tv1.setText(fd.restaurantName);
            tv1.setWidth(1);
            tv1.setGravity(Gravity.CENTER);

            tr0.addView(tv1);

            TextView tv2 = new TextView(this);
            tv2.setText(fd.people);
            tv2.setWidth(1);
            tv2.setGravity(Gravity.CENTER);
            tr0.addView(tv2);

            RadioButton rd = new RadioButton(this);
        }
        return false;
    }
}