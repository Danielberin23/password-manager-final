package com.example.password_manager;

import static com.example.password_manager.util.AESUtils.md5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.password_manager.util.AESUtils;



import java.io.IOException;
import java.security.GeneralSecurityException;

public class modifyActivity extends AppCompatActivity {
    public static final String TAG = "MODIFY";
    public static final String EXTRA_DELETE = "DELETE";
    public static final String EXTRA_PROVIDER_NAME = "com.example.password_manager.EXTRA_PROVIDER_NAME";
    public static final String EXTRA_ID = "com.example.password_manager.EXTRA_ID";
    public static final String EXTRA_ENCRYPT = "com.example.password_manager.EXTRA_ENCRYPT";
    public static final String EXTRA_EMAIL = "com.example.password_manager.EXTRA_EMAIL";
    public static final String EXTRA_IV = "com.example.password_manager.EXTRA_IV";
    public static final String EXTRA_SALT = "com.example.password_manager.EXTRA_SALT";
    private static final String PREFS_NAME = "appEssentials";
    EditText newPassword;
    TextView emailText, oldPassword;
    String provName, email, passwd, decPass;
    CheckBox show_change_password, show_password;
    MasterKey masterKey = null;
    SharedPreferences sharedPreferences = null;
    String PREF_KEY_SECURE_CORE_MODE = "SECURE_CORE";
    Button changePasswordButton, updateBtn, deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        initViews();
        try {
            //x.security
            masterKey = new MasterKey.Builder(getApplicationContext(), MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            //init sharedPef
            sharedPreferences = EncryptedSharedPreferences.create(
                    getApplicationContext(),
                    PREFS_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        if (sharedPreferences.getBoolean(PREF_KEY_SECURE_CORE_MODE, false)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        String sha = sharedPreferences.getString("HASH", "0");
        String HASH = md5(sha);
        Intent intent = getIntent();
        provName = intent.getStringExtra(EXTRA_PROVIDER_NAME);
        email = intent.getStringExtra(EXTRA_EMAIL);
        passwd = intent.getStringExtra(EXTRA_ENCRYPT);
        try {
            String decEmail = AESUtils.decrypt(email, HASH);
            decPass = AESUtils.decrypt(passwd, HASH);

            emailText.setText(decEmail);
            oldPassword.setText(decPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initViews()
    {
        emailText = findViewById(R.id.modify_email);
        oldPassword = findViewById(R.id.modify_old_password);
        show_password = findViewById(R.id.show_password);
        changePasswordButton = findViewById(R.id.change_password_button);
        newPassword = findViewById(R.id.modify_new_password);
        show_change_password = findViewById(R.id.modify_show_password);
        updateBtn = findViewById(R.id.modify_update);
        deleteBtn = findViewById(R.id.modify_delete);

        updateBtn.setEnabled(false);
    }
}