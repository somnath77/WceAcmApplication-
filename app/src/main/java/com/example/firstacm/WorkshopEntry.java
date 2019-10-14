package com.example.firstacm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class WorkshopEntry extends AppCompatActivity {

    Button addEntry;
    private RecyclerView mEntries;
    private DatabaseReference mDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_workshop_entry);

        Toolbar tbar=findViewById(R.id.tool_bar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Entries");

        mDB = FirebaseDatabase.getInstance().getReference().child("Entries");
        mDB.keepSynced(true);

        mEntries = (RecyclerView) findViewById(R.id.idRecycleview);
        mEntries.setHasFixedSize(true);
        mEntries.setLayoutManager(new LinearLayoutManager(this));

        addEntry=(Button)findViewById(R.id.btnAddEntry);
        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddEntry.class));

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DataTypes,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DataTypes , BlogViewHolder>
                (DataTypes.class, R.layout.card_entry, BlogViewHolder.class, mDB) {
            @Override
            protected void populateViewHolder(BlogViewHolder blogViewHolder, DataTypes dt, int i) {
                blogViewHolder.setTextView_fname(getApplicationContext(),dt.getFullName());
                blogViewHolder.setTextView_cls(getApplicationContext(),dt.getClassName());
                blogViewHolder.setTextView_cnm(getApplicationContext(),dt.getCollageName());
            }
        };
        mEntries.setAdapter(firebaseRecyclerAdapter );

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void setTextView_fname(Context applicationContext, String fullName)
        {
            TextView   textView_fname = (TextView)mView.findViewById(R.id.txtShowFN);
            textView_fname.setText(fullName);
        }
        public void setTextView_cls(Context applicationContext, String className)
        {
            TextView textView_cls = (TextView) mView.findViewById(R.id.txtShowCls);
            textView_cls.setText(className);
        }
        public void setTextView_cnm(Context applicationContext, String collageName)
        {
            TextView textView_cnm = (TextView) mView.findViewById(R.id.txtShowCnm);
            textView_cnm.setText(collageName);
        }
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }

    
}
