package assignment2.poly.eduvn.assignment2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dao.DAOChi;
import dao.DAOTaiKhoan;
import model.Chi;

public class DuDinh extends AppCompatActivity {
	ListView lvDuDinh;
	custom_adapter_dd adapter;
	ArrayList<Chi> arrChi;
	LinearLayout child;
	LinearLayout l;
	LinearLayout llMenu;
	ImageButton ibXoa, ibLuu;

	DAOChi daoChi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_du_dinh);
		lvDuDinh = (ListView) findViewById(R.id.lvDuDinh);
		DAOChi daoChi = new DAOChi();
		arrChi = daoChi.getList(getBaseContext(), 0);

		adapter = new custom_adapter_dd(arrChi);
		lvDuDinh.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void myClickHandler(View v) {
		daoChi = new DAOChi();
		child = (LinearLayout) v.getParent();

		l = (LinearLayout) child.getParent();

		llMenu = (LinearLayout) l.findViewById(R.id.llMenu);
		ibXoa = (ImageButton) llMenu.findViewById(R.id.ibXoaDuDinh);
		ibLuu = (ImageButton) llMenu.findViewById(R.id.ibLuuDuDinh);
		if(llMenu.getVisibility() == View.VISIBLE){
			llMenu.setVisibility(View.GONE);
		}
		else {
			llMenu.setVisibility(View.VISIBLE);
		}

		ibXoa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				TextView idDuDinh = (TextView) l.findViewById(R.id.tvIdDuDinh);
				arrChi.remove(lvDuDinh.getPositionForView(l));
				adapter.notifyDataSetChanged();
				daoChi.delete(DuDinh.this, Long.parseLong(idDuDinh.getText().toString()));

			}
		});

		ibLuu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView idDuDinh = (TextView) l.findViewById(R.id.tvIdDuDinh);
				TextView title = (TextView) child.findViewById(R.id.tvTitle);
				title.setText(title.getText()+" (đã chi)");
				TextView idTKDuDinh = (TextView) l.findViewById(R.id.tvIdDuDinh);
				TextView tienDuDinh = (TextView) l.findViewById(R.id.tvTienDuDinh);
				tinhTien(Double.parseDouble(tienDuDinh.getText().toString()),Long.parseLong(idTKDuDinh.getText().toString()) );
				daoChi.luuDuDinh(Long.parseLong(idDuDinh.getText().toString()),DuDinh.this);

			}
		});
	}


	private void tinhTien(double tien, Long idTaiKhoan) {
		DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
		double trongTaiKhoan = daoTaiKhoan.getSoTien(idTaiKhoan, DuDinh.this);

		daoTaiKhoan.update(idTaiKhoan,(trongTaiKhoan - tien), DuDinh.this);
	}
}
