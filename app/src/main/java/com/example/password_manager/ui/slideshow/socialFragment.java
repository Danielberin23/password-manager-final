package com.example.password_manager.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.password_manager.Interfaces.PasswordCallback;
import com.example.password_manager.Password;
import com.example.password_manager.R;
import com.example.password_manager.adapter.PasswordAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class socialFragment extends Fragment {

    private RecyclerView main_LST_social;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel galleryViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        return inflater.inflate(R.layout.fragment_social,container,false);
    }
    private void initViews() {
        ArrayList<Password> list = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference titleRef = db.getReference().child("password");
        titleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot Snapshot : snapshot.getChildren()) {
                    list.add(snapshot.getValue(Password.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        PasswordAdapter passwordAdapter = new PasswordAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_social.setLayoutManager(linearLayoutManager);
        main_LST_social.setAdapter(passwordAdapter);
        passwordAdapter.setPasswordcallBack(new PasswordCallback() {
                                                        @Override
                                                        public void itemClicked (Password pass,int position){

                                                        }

                                                        @Override
                                                        public void favoriteClicked (Password pass,int position){

                                                        }
                                                    });
    }
}


