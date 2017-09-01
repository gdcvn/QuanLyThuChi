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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import dao.DAOChi;
import dao.DAOTaiKhoan;
import model.Chi;
import model.TaiKhoan;

import static assignment2.poly.eduvn.assignment2.R.id.btnTaiKhoan;

/**
 * Created by vuong on 24/10/2016.
 */

public class FragmentChi extends Fragment {
    EditText etSoTien;
    Button btnLuu, btnThoiGian, btnMoTa, btnMucChi, btnTaiKhoanChi, btnLuuKhoanChi;
    Chi objChi;
    String moTa, chi = "Đầu tư";
    AlertDialog alertDialog;
    ArrayList<String> items;
    ArrayList<Long> itemsId;
    Long id;
    public FragmentChi(){

        objChi = new Chi();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_chi, container, false);

        etSoTien = (EditText) rootView.findViewById(R.id.etSoTien);
        btnLuuKhoanChi = (Button) rootView.findViewById(R.id.btnLuuKhoanChi);
        btnThoiGian = (Button) rootView.findViewById(R.id.btnThoiGian);
        btnLuu = (Button) rootView.findViewById(R.id.btnLuuKhoanChi);
        btnMoTa = (Button) rootView.findViewById(R.id.btnMoTa);
        btnMucChi = (Button) rootView.findViewById(R.id.btnMucChi);
        btnTaiKhoanChi = (Button) rootView.findViewById(btnTaiKhoan);

        items = new ArrayList<>();
        itemsId = new ArrayList<>();
        DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
        for(TaiKhoan tk : daoTaiKhoan.getList(getActivity())){
            items.add(tk.getTenTaiKhoan());
            itemsId.add(tk.getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.ct_list, items);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Lưu !", Toast.LENGTH_SHORT).show();
            }
        });

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
                        btnThoiGian.setText("Thời gian    -    "+dayOfMonth+"/"+(month+1)+"/"+year);
                        objChi.setThoiGian(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
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

                                if(moTa.length() >26){
                                    btnMoTa.setText("Mô tả  :  "+moTa.substring(0,20));
                                }
                                else{
                                    btnMoTa.setText("Mô tả  :  "+moTa);
                                }
                                objChi.setMoTa(moTa);
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

        btnMucChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] mucChi = new String[]{"Ăn uống", "Đầu tư", "Mua thuốc", "Liên hoan", "Đóng hội"};
                AlertDialog dialogMucThu = new AlertDialog.Builder(getActivity())
                        .setTitle("Chọn mục chi")
                        .setSingleChoiceItems(mucChi, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                chi = mucChi[which].toString();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btnMucChi.setText("Mục thu    -   "+chi);
                                objChi.setMucChi(chi);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();

                dialogMucThu.show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose account");
        builder.setIcon(R.drawable.ic_account_balance_wallet_black_24dp);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnTaiKhoanChi.setText("Tài khoản chi    -   "+items.get(which));
                objChi.setTaiKhoanChi(items.get(which));
                id = itemsId.get(which);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        alertDialog = builder.create();

        btnTaiKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.show();
            }
        });


        RadioGroup rb = (RadioGroup) rootView.findViewById(R.id.rgRadio);
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rbDaChi:
                        btnThoiGian.setEnabled(true);
                        break;

                    case R.id.rbChuaChi:
                        btnThoiGian.setEnabled(false);
                        break;

                    default:

                        break;

                }
            }

        });

        btnLuuKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double t = Double.parseDouble(etSoTien.getText().toString());
                if(btnThoiGian.isEnabled()){
                    objChi.setDaChi(1);
                    objChi.setIdTaiKhoan(Long.parseLong("0"));
                    tinhTien(t, id);

                }
                else{
                    objChi.setDaChi(0);
                    objChi.setThoiGian("");
                    objChi.setIdTaiKhoan(id);
                }

                DAOChi daoChi = new DAOChi();
                objChi.setId(new Date().getTime());
                objChi.setSoTien(t);

                daoChi.them(objChi, getActivity());
            }
        });
        return rootView;
    }

    private void tinhTien(double tien, Long idTaiKhoan) {
        DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
        double trongTaiKhoan = daoTaiKhoan.getSoTien(idTaiKhoan, getActivity());

        daoTaiKhoan.update(idTaiKhoan,(trongTaiKhoan - tien), getActivity());
    }
}
