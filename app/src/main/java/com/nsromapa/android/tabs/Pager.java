package com.nsromapa.android.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nsromapa.android.tab1pakage.Tab1Fragment;
import com.nsromapa.android.tab2pakage.Tab2Fragment;
import com.nsromapa.android.tab3pakage.Tab3Fragment;

/**
 * Created by SAY on 10/08/2018.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return tab1Fragment;

            case 1:
                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return tab2Fragment;

            case 2:
                Tab3Fragment tab3Fragment = new Tab3Fragment();
                return tab3Fragment;

            case 3:
                Tab4Fragment tab4Fragment = new Tab4Fragment();
                return tab4Fragment;

            case 4:
                Tab5Fragment tab5Fragment = new Tab5Fragment();
                return tab5Fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
