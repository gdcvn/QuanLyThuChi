package assignment2.poly.eduvn.assignment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vuong on 24/10/2016.
 */

public class MenuFragment extends Fragment {

    Fragment frag;
    FragmentTransaction fragTransction;

    public MenuFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menu_multi, container, false);

        frag = new FragmentThu();
        fragTransction = getFragmentManager().beginTransaction().add(R.id.container, frag);
        fragTransction.commit();

        final Button btnThu = (Button) view.findViewById(R.id.btnBackThu);
        final Button btnChi = (Button) view.findViewById(R.id.btnForwardChi);


        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnThu.setVisibility(View.INVISIBLE);
                btnChi.setVisibility(View.VISIBLE);
                frag = new FragmentThu();
                fragTransction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransction.commit();
            }
        });
        btnChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnThu.setVisibility(View.VISIBLE);
                btnChi.setVisibility(View.INVISIBLE);
                frag = new FragmentChi();
                fragTransction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransction.commit();
            }
        });

        return view;
    }
}
