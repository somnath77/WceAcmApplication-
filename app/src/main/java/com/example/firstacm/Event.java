package com.example.firstacm;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Event extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_event);
        Toolbar tbar=findViewById(R.id.tool_bar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Events");


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView) findViewById(R.id.myrecycleview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Event_card, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Event_card, BlogViewHolder>
                (Event_card.class, R.layout.card_easy_flip, BlogViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder blogViewHolder, Event_card blog, int i) {
                blogViewHolder.setImage(getApplicationContext(), blog.getImage());
                blogViewHolder.setTextView_description(getApplicationContext(),blog.getDescription());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter );

    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTextView_description(Context applicationContext, String description)
        {
            TextView textView_fname = (TextView)mView.findViewById(R.id.event_description);
            textView_fname.setText(description);
        }

        public void setImage(Context ctx, String image) {
            ImageView imageview = (ImageView) mView.findViewById(R.id.banner_goes_here);
            Picasso.get().load(image).fit().centerCrop()
                    .placeholder(R.drawable.dummy)
                    .into(imageview);
        }
    }

}