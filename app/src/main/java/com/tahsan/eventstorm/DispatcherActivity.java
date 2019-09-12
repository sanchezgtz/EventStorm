package com.tahsan.eventstorm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tahsan.eventstorm.utilerias.Utileria;

public class DispatcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatcher);

        Class<?> activityClass;
        try {

            String activity = Utileria.getPreference_String(this, getString(R.string.lastActivity));
            activityClass = Class.forName(activity);
        } catch(ClassNotFoundException ex) {
            activityClass = LoginActivity.class;
        }

        startActivity(new Intent(this, activityClass));
        finish();
    }
}
