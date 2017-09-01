package assignment2.poly.eduvn.assignment2;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Thu extends AppCompatActivity {
	Button btnThoiGian, btnTaiKhoan, btnMucThu, btnForwardChi, btnMoTa;
	AlertDialog alertDialog;
	String mucThu = "Làm thêm";
	private String moTa, taiKhoan, thoiGian;
	private String[] items = {"Ngân hàng","ATM","Ví vợ", "Tiết kiệm"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thu);

		btnMoTa = (Button) findViewById(R.id.btnMoTa);
		btnForwardChi = (Button) findViewById(R.id.btnForwardChi);
		btnMucThu = (Button) findViewById(R.id.btnMucThu);
		btnTaiKhoan = (Button) findViewById(R.id.btnTaiKhoan);
		btnThoiGian = (Button) findViewById(R.id.btnThoiGian);
		btnThoiGian.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DatePickerDialog datePickerDialog = new DatePickerDialog(Thu.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
						btnThoiGian.setText("Thời gian    -    "+dayOfMonth+"/"+(month+1)+"/"+year);
					}
				}, 1997, 8, 13);
				datePickerDialog.show();
			}
		});

		btnMucThu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String[] mucthu = new String[]{"Tiền lương", "Đầu tư", "Làm thêm", "Lương thưởng" };
				AlertDialog dialogMucThu = new AlertDialog.Builder(Thu.this)
						.setTitle("Chọn mục thu")
						.setSingleChoiceItems(mucthu, 2, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								mucThu = mucthu[which].toString();
							}
						})
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								btnMucThu.setText("Mục thu    -   "+mucThu);
							}
						})
						.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(Thu.this, "Cancel", Toast.LENGTH_SHORT).show();
							}
						})
						.create();

				dialogMucThu.show();
			}
		});

		AlertDialog.Builder builder = new AlertDialog.Builder(Thu.this);
		builder.setTitle("Choose account");
		builder.setIcon(R.drawable.ic_account_balance_wallet_black_24dp);
		builder.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				btnTaiKhoan.setText("Vào tài khoản    -   "+items[which]);
			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
			}
		});

		builder.setCancelable(false);
		alertDialog = builder.create();

		btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(Thu.this, "ok", Toast.LENGTH_SHORT).show();
				alertDialog.show();
			}
		});

		btnForwardChi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		btnMoTa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater layoutInflaterAndroid = LayoutInflater.from(Thu.this);
				View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
				AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Thu.this);
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
									 btnMoTa.setText("Mô tả : "+moTa.substring(0,20));
								 }
								else{
									 btnMoTa.setText("Mô tả : "+moTa);
								 }

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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
