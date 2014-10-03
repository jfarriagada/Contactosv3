package com.example.konarr.contactosv3.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.example.konarr.contactosv3.CrearContacto;
import com.example.konarr.contactosv3.ListaContacto;

/**
 * Created by konarr on 30-09-14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CrearContacto();
            case 1: return new ListaContacto();
        }
        return null;
    }
}
