package com.tahsan.eventstorm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.tahsan.eventstorm.adapter.ContenidoFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContenidoEventoActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_evento);

        Toolbar myToolbar = findViewById(R.id.toolbar_contenido);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(getString(R.string.titulo_infoevento));

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final BottomNavigationView navigation = findViewById(R.id.nv_contenido);
        navigation.setOnNavigationItemSelectedListener(this);

        mViewPager = findViewById(R.id.viewpager_contenido);

        ContenidoFragmentAdapter adapterViewPager = new ContenidoFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                //navigation.getMenu().getItem(position).setChecked(true);
            }
        });

        if (savedInstanceState == null) {
            onNavigationItemSelected(navigation.getMenu().findItem(R.id.navigation_evento));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_evento:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_actividades:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_instructores:
                mViewPager.setCurrentItem(2);
                return true;
            case R.id.navigation_alojamiento:
                mViewPager.setCurrentItem(3);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
