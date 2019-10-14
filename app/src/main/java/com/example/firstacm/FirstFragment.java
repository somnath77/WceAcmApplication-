package com.example.firstacm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {


    private RecyclerView mBlogList;
    private DatabaseReference mdatabase;
    View v;

    public FirstFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_first, container, false);
        return rootView;

    }
    public void  onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.v=view;
        mBlogList = (RecyclerView) view.findViewById(R.id.RecycleView_first);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mdatabase= FirebaseDatabase.getInstance().getReference().child("MainBoard");
        mdatabase.keepSynced(true);

        FirebaseRecyclerAdapter<people, FirstFragment.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<people, FirstFragment.BlogViewHolder>
                (people.class, R.layout.card_member, FirstFragment.BlogViewHolder.class, mdatabase) {
            @Override
            protected void populateViewHolder(FirstFragment.BlogViewHolder blogViewHolder, people ppl, int i) {
                blogViewHolder.setImage(getActivity(),ppl.getImage());
                blogViewHolder.setTextView_name(getActivity(),ppl.getName());
                blogViewHolder.setTextView_post(getActivity(),ppl.getPost());
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

        public void setTextView_name(Context applicationContext, String name)
        {
            TextView textView_fname = (TextView)mView.findViewById(R.id.nameOfMember);
            textView_fname.setText(name);
        }
        public void setTextView_post(Context applicationContext, String post)
        {
            TextView textView_cls = (TextView) mView.findViewById(R.id.postOfMember);
            textView_cls.setText(post);
        }

        public void setImage(Context ctx, String image) {
            ImageView imageview = (ImageView) mView.findViewById(R.id.showImage);
            Picasso.get().load(image).fit().centerCrop()
                    .placeholder(R.drawable.dummy)
                    .into(imageview);
        }
    }
}