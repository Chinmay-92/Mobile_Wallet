package com.psl.myapplication;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentAdapter extends FragmentPagerAdapter {

public static int pos = 0;

private List<Fragment> myFragments;

public MyFragmentAdapter(FragmentManager fm, List<Fragment> myFrags) {
    super(fm);
    myFragments = myFrags;
}

@Override
public Fragment getItem(int position) {

    return myFragments.get(position);

}

@Override
public int getCount() {

    return myFragments.size();
}

@Override
public CharSequence getPageTitle(int position) {

    setPos(position);

    String PageTitle = "no position";

    switch(pos)
    {
        case 0:
                PageTitle = "send money";
                break;
        case 1:
                PageTitle = "add money";
                break;
        case 2:
                PageTitle = "Adhaar verification";
                break;
        case 3:
                PageTitle = "page 4";
                break;
        case 4:
                PageTitle = "page 5";
                break;
        case 5: 
                PageTitle = "page 6";
                break;
        case 6:
                PageTitle = "page 7";
                break;
    }
    return PageTitle;
}

public static int getPos() {
    return pos;
}

 public static void setPos(int pos) {
    MyFragmentAdapter.pos = pos;
 }
}