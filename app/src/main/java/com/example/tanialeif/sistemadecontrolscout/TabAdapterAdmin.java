package com.example.tanialeif.sistemadecontrolscout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.tanialeif.sistemadecontrolscout.TabsAdmin.*;
import android.support.v4.view.PagerAdapter;

public class TabAdapterAdmin extends FragmentStatePagerAdapter {

    int nTabs;

    public TabAdapterAdmin(FragmentManager fm, int numberTabs){
        super(fm);
        this.nTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                TabLineas tab1 = new TabLineas();
                return tab1;
            case 1:
                TabObjetivos tab2 = new TabObjetivos();
                return tab2;
            case 3:
                TabSecciones tab3 = new TabSecciones();
                return tab3;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 0;
    }
}
