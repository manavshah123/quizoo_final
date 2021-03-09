package com.app.quizoofinal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    private List<String> list= new ArrayList<String>();
    private CategoryAdapter categoryAdapter;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.themedarkcolor));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.transperent));

        ////////////// Category_Card /////////////////////////////////

        recyclerView= findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference =FirebaseDatabase.getInstance().getReference().child("Category");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot:snapshot.getChildren()){
                    String categoryDatainfo= postSnapshot.getValue(String.class);
                    list.add(categoryDatainfo);
                    recyclerView.setAdapter(categoryAdapter);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        }

}