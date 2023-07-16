package com.example.password_manager.ui.home;

import android.content.Intent;
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
import com.example.password_manager.addActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class passwordFragment extends Fragment {

    private static final String PROVIDER="mail";
    private RecyclerView main_LST_passwords;
    private FloatingActionButton add;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PasswordViewModel passwordViewModel =
                new ViewModelProvider(this).get(PasswordViewModel.class);
        View inf=inflater.inflate(R.layout.fragment_password,container,false);
        main_LST_passwords=inf.findViewById(R.id.recyclerPassword);
        add= inf.findViewById(R.id.add_aaa);


        initViews();
        add.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), addActivity.class);

            intent.putExtra(addActivity.EXTRA_PROVIDER,PROVIDER);
            startActivity(intent);

        });

        return inf;



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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
             linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
             main_LST_passwords.setLayoutManager(linearLayoutManager);
            main_LST_passwords.setAdapter(passwordAdapter);
            passwordAdapter.setPasswordcallBack(new

            PasswordCallback() {
                @Override
                public void itemClicked (Password pass,int position){

                }

                @Override
                public void favoriteClicked (Password pass,int position){

                }
            });
        };
    }
