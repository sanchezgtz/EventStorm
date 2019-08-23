package com.tahsan.eventstorm.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tahsan.eventstorm.fragmentos.ActividadesFragment;
import com.tahsan.eventstorm.fragmentos.AlojamientoFragment;
import com.tahsan.eventstorm.fragmentos.EventoFragment;
import com.tahsan.eventstorm.fragmentos.InstructorFragment;

public class ContenidoFragmentAdapter extends FragmentPagerAdapter {

    public ContenidoFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EventoFragment.newInstance();
            case 1:
                return ActividadesFragment.newInstance();
            case 2:
                return InstructorFragment.newInstance();
            case 3:
                return AlojamientoFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
