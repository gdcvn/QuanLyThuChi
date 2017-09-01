package assignment2.poly.eduvn.assignment2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.DAOTaiKhoan;
import model.TaiKhoan;

public class MainActivity extends AppCompatActivity {
    TextView tvTong;
    ListView lvTaiKhoan;
    Spinner spSpinner;
    private EditText editText;
    AlertDialog alertDialog;
    custom_adapter_tk adapter_tk;

    String loai = "Ngân hàng";
    DAOTaiKhoan daoTaiKhoan;
    private ArrayList<TaiKhoan> arrTaiKhoan;
    private String[] items = {"Ngân hàng", "ATM", "Ví vợ", "Tiết kiệm", "Đầu tư", "Buôn bán"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoTaiKhoan = new DAOTaiKhoan();
        arrTaiKhoan = daoTaiKhoan.getList(getBaseContext());
        adapter_tk = new custom_adapter_tk(arrTaiKhoan, MainActivity.this);
        lvTaiKhoan = (ListView) findViewById(R.id.lvTaiKhoan);
        lvTaiKhoan.setAdapter(adapter_tk);
        tvTong = (TextView) findViewById(R.id.tvTong);
        DecimalFormat df = new DecimalFormat("###,###.## đ");
        tvTong.setText(getString(R.string.total) + df.format(daoTaiKhoan.getSum(getBaseContext())));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                    dialog();
                break;
            default:
                break;
        }
        return true;
    }


    public void myClickHandler(View v) {

        RelativeLayout r = (RelativeLayout) v.getParent();
        TextView id = (TextView) r.findViewById(R.id.tvId);
        DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
        daoTaiKhoan.delete(getBaseContext(), Long.parseLong(id.getText().toString()));

        arrTaiKhoan.remove(lvTaiKhoan.getPositionForView(r));
        adapter_tk.notifyDataSetChanged();
        DecimalFormat df = new DecimalFormat("###,###.## đ");
        tvTong.setText(getString(R.string.total) + df.format(daoTaiKhoan.getSum(getBaseContext())));
    }


    public void dialog(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, items);
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText etNameAccount = (EditText) alertLayout.findViewById(R.id.et_accountname);
        final Spinner spTypeAccount = (Spinner) alertLayout.findViewById(R.id.btn_typeaccount);
        final EditText etMoney = (EditText) alertLayout.findViewById(R.id.et_money);

        spTypeAccount.setPrompt("Loại tài khoản");
        spTypeAccount.setAdapter(adapter);

        spTypeAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loai = items[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Thêm tài khoản");
        // this is set the view from XML inside AlertDialog
        alert.setIcon(R.drawable.ic_add_box_black_24dp);
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaiKhoan  taiKhoan = new TaiKhoan();
                taiKhoan.setLoaiTaiKhoan(loai);
                taiKhoan.setTenTaiKhoan(etNameAccount.getText().toString());
                taiKhoan.setSoTien(Double.parseDouble(etMoney.getText().toString()));
                taiKhoan.setId(new Date().getTime());
                arrTaiKhoan.add(taiKhoan);
                daoTaiKhoan.them(taiKhoan, getBaseContext());
                adapter_tk.notifyDataSetChanged();
                tvTong = (TextView) findViewById(R.id.tvTong);
                DecimalFormat df = new DecimalFormat("###,###.## đ");
                tvTong.setText(getString(R.string.total) + df.format(daoTaiKhoan.getSum(getBaseContext())));
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}
