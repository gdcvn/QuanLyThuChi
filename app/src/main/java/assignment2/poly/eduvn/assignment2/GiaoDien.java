package assignment2.poly.eduvn.assignment2;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class GiaoDien extends TabActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien);


        TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("first tab")
                .setIndicator("", getResources().getDrawable(R.drawable.ic_account_balance_wallet_black_24dp))
                .setContent(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        tabHost.addTab(tabHost.newTabSpec("second tab")
                .setIndicator("", getResources().getDrawable(R.drawable.ic_content_paste_black_24dp))
                .setContent(new Intent(this, FragmentThuChi.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        tabHost.addTab(tabHost.newTabSpec("third tab")
                .setIndicator("", getResources().getDrawable(R.drawable.ic_event_note_black_24dp))
                .setContent(new Intent(this, DuDinh.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        tabHost.addTab(tabHost.newTabSpec("four tab")
                .setIndicator("", getResources().getDrawable(R.drawable.ic_poll_black_24dp))
                .setContent(new Intent(this, BaoCao.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));


    }



}
