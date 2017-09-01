package assignment2.poly.eduvn.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.TaiKhoan;

public class custom_adapter_tk extends BaseAdapter {
	ArrayList<TaiKhoan> arrTaiKhoan;
	Context ct;
	TaiKhoan tk;

	public custom_adapter_tk(ArrayList<TaiKhoan> arrTaiKhoan, Context ct) {
		super();
		this.ct = ct;
		this.arrTaiKhoan = arrTaiKhoan;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrTaiKhoan.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		LayoutInflater inflater = LayoutInflater.from(arg2.getContext());
		View rowView = inflater.inflate(R.layout.list_item, arg2, false);


		ImageView icon = (ImageView) rowView.findViewById(R.id.ivAnh);
		TextView tenTaiKhoan = (TextView) rowView
				.findViewById(R.id.tvTenTaiKhoan);
		TextView soTien = (TextView) rowView
				.findViewById(R.id.tvSoTien);
		
		tk = arrTaiKhoan.get(arg0);
		if(tk.getLoaiTaiKhoan().toLowerCase().equals("atm")){
			icon.setImageResource(R.drawable.money);
		}

		TextView id = (TextView) rowView.findViewById(R.id.tvId);
		
		tenTaiKhoan.setText(tk.getTenTaiKhoan());
		DecimalFormat df = new DecimalFormat("###,###.## đ");
		soTien.setText("Còn : "+df.format(tk.getSoTien()));
		id.setText(tk.getId()+"");

//		ImageButton ibSuaXoa = (ImageButton) rowView.findViewById(R.id.ibSuaXoa);
//		ibSuaXoa.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				RelativeLayout r = (RelativeLayout) v.getParent();
//				TextView id = (TextView) r.findViewById(R.id.tvId);
//				DAOTaiKhoan daoTaiKhoan = new DAOTaiKhoan();
//				daoTaiKhoan.delete(ct,Long.parseLong(id.getText().toString()));
//			}
//		});

		return rowView;
	}

}
