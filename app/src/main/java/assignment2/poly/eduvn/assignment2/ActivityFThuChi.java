package assignment2.poly.eduvn.assignment2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityFThuChi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fthu_chi);
    }

//    public void selectFrag(View view) {
//        Fragment fr;
//
//        if(view == findViewById(R.id.btnForwardChi)) {
//            fr = new FragmentTwo();
//
//        }else {
//            fr = new FragmentOne();
//        }
//
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_place, fr);
//        fragmentTransaction.commit();
//
//    }

}
