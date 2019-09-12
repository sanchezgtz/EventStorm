package com.tahsan.eventstorm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.tahsan.eventstorm.utilerias.Utileria;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Toolbar myToolbar = findViewById(R.id.toolbar_perfil);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(getString(R.string.titulo_perfil));

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        Utileria.savePreference_String(this, getString(R.string.lastActivity), getClass().getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
