package com.example.password_manager.Interfaces;


import com.example.password_manager.Password;

public interface PasswordCallback {
    void itemClicked(Password pass, int position);

    void favoriteClicked(Password pass, int position);
}
