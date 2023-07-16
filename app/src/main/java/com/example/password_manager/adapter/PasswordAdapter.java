package com.example.password_manager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.password_manager.ImageLoader;
import com.example.password_manager.Interfaces.PasswordCallback;
import com.example.password_manager.Password;
import com.example.password_manager.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder> {
    private final ArrayList<Password> passwords;

    public PasswordAdapter(ArrayList<Password> passwords) {
        this.passwords = passwords;
    }

    private PasswordCallback passwordcallBack;

    public void setPasswordcallBack(PasswordCallback passwordcallBack) {
        this.passwordcallBack = passwordcallBack;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        PasswordViewHolder passwordViewHolder = new PasswordViewHolder(view);
        return passwordViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {
        Password password = getItem(position);
        holder.mail.setText(password.getEmail());
        holder.provider.setText(password.getProvider());

        ImageLoader.getInstance().loadImage(password.getImg(), holder.password_Provider_img);
    }


    public int getItemCount() {
        return this.passwords == null ? 0 : this.passwords.size();
    }

    private Password getItem(int position) {
        return this.passwords.get(position);
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder {
        private final TextView provider;
        private final TextView mail;
        private final ShapeableImageView password_Provider_img;

        public PasswordViewHolder(@NonNull View itemView) {
            super(itemView);
            provider = itemView.findViewById(R.id.provider);
            mail = itemView.findViewById(R.id.imp_cat);
            password_Provider_img = itemView.findViewById(R.id.ic_IMG_prov);
            {
                password_Provider_img.setOnClickListener(v -> {
                    if (passwordcallBack != null) {
                        passwordcallBack.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition());
                    }

                });
                itemView.setOnClickListener(v -> {
                    if (passwordcallBack != null) {
                        passwordcallBack.favoriteClicked(getItem(getAdapterPosition()), getAdapterPosition());
                    }

                });
            }
        }
    }
}
