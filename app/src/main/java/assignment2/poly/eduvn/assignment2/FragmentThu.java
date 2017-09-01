package assignment2.poly.eduvn.assignment2;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao.DAOTaiKhoan;
import dao.DAOThu;
import model.TaiKhoan;
import model.Thu;

/**
 * Created by vuong on 24/10/2016.
 */

public class FragmentThu extends Fragment {
    Thu thu;
    EditText soTien;
    Button btnThoiGian, btnTaiKhoan, btnMucThu, btnForwardChi, btnMoTa, btnLuu;
    AlertDialog alertDialog;
    String mucThu = "Làm thêm";
    private String moTa, taiKhoan, thoiGian;
    ArrayList<String> items;
    ArrayList<Long> itemsId;
    Long id;
    public FragmentThu() {
        thu = new Thu();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_thu, container, false);

        btnLuu = (Button) rootView.findViewById(R.id.btnLuuKhoanThu);
        soTien = (EditText) rootView.findViewById(R.id.etSoTien);
        btnMoTa = (Button) rootView.findViewById(R.id.btnMoTa);
        btnMucThu = (Button) rootView.findViewById(R.id.btnMucThu);
        btnTaiKhoan = (Button) rootView.findViewById(R.id.btnTaiKhoan);
        btnThoiGian = (Button) rootView.findViewById(R.id.btnThoiGian);
        items = new ArrayList<>();
        itemsId = new ArrayList<>();
        DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
        for (TaiKhoan tk : daoTaiKhoan.getList(getActivity())) {
            items.add(tk.getTenTaiKhoan());
            itemsId.add(tk.getId());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.ct_list, items);

        btnThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String thoiGian = dayOfMonth + "/" + (month + 1) + "/" + year;
                        btnThoiGian.setText("Thời gian    -    " + thoiGian);
                        thu.setThoiGian(thoiGian);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnMucThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] mucthu = new String[]{"Tiền lương", "Đầu tư", "Làm thêm", "Lương thưởng"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Set the dialog title
                builder.setTitle("Chọn mục thu")
                        // Specify the list array, the items to be selected by default (null for none),
                        // and the listener through which to receive callbacks when items are selected
                        .setSingleChoiceItems(mucthu, 2, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mucThu = mucthu[which].toString();
                            }
                        })
                        // Set the action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                btnMucThu.setText("Mục thu    -   " + mucThu);
                                thu.setMucThu(mucThu);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .create();

                builder.show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose account");
        builder.setIcon(R.drawable.ic_account_balance_wallet_black_24dp);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnTaiKhoan.setText("Vào tài khoản    -   " + items.get(which));
                thu.setTaiKhoanThu(items.get(which));
                id = itemsId.get(which);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        alertDialog = builder.create();

        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();
            }
        });


        btnMoTa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());
                View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
                alertDialogBuilderUserInput.setView(mView);

                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                userInputDialogEditText.setText(moTa);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                moTa = "";
                                moTa = userInputDialogEditText.getText().toString();

                                if (moTa.length() > 26) {
                                    btnMoTa.setText("Mô tả  :  " + moTa.substring(0, 20));
                                } else {
                                    btnMoTa.setText("Mô tả  :  " + moTa);
                                }

                                thu.setMoTa(moTa);

                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double t = Double.parseDouble(soTien.getText().toString());
                DAOThu daoThu = new DAOThu();
                thu.setId(new Date().getTime());
                thu.setSoTien(t);
                daoThu.them(thu, getActivity());
                tinhTien(t, id);
            }
        });
        return rootView;
    }

    private void tinhTien(double tien, Long idTaiKhoan) {
        DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
        double trongTaiKhoan = daoTaiKhoan.getSoTien(idTaiKhoan, getActivity());

        daoTaiKhoan.update(idTaiKhoan,(tien + trongTaiKhoan), getActivity());
    }
}
