package com.tahsan.eventstorm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tahsan.eventstorm.adapter.EventoListAdapter;
import com.tahsan.eventstorm.pojo.Evento;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    private ArrayList<Evento> mEventos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_eventos);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getApplicationContext());
        rv.setLayoutManager(llm);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mEventos = new ArrayList<>();
        mEventos.add(new Evento(1, "Evento 1", "imagen"));
        mEventos.add(new Evento(2, "Evento 2", "imagen"));
        mEventos.add(new Evento(3, "Evento 3", "imagen"));
        mEventos.add(new Evento(4, "Evento 4", "imagen"));

/*recycler.setAdapter(new ContentAdapter(items, new ContentAdapter.OnItemClickListener() {
    @Override public void onItemClick(ContentItem item) {
        Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
    }
}));*/
        rv.setAdapter(new EventoListAdapter(this.getApplicationContext(), mEventos, new EventoListAdapter.OnItemClickListener() {
            @Override public void onItemClick(Evento item) {
                Intent intent = new Intent(getApplicationContext(), ContenidoEventoActivity.class);
                startActivity(intent);
            }
        }));

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
//To do//
                            return;
                        }

// Get the Instance ID token//
                        String token = task.getResult().getToken();
                        String msg = getString(R.string.fcm_token, token);
                        Log.d("MainActivity", msg);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Get menu inflater.
        MenuInflater menuInflater = getMenuInflater();

        // Inflate the menu with custom menu items.
        menuInflater.inflate(R.menu.principal_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.principla_fav:
                Intent intent = new Intent(getApplicationContext(), EventosInscritosActivity.class);
                startActivity(intent);
                break;
            case R.id.principal_perfil:
                intent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
