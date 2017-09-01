package assignment2.poly.eduvn.assignment2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Chi;

/**
 * Created by vuong on 25/10/2016.
 */

public class custom_adapter_dd extends BaseAdapter {

    ArrayList<Chi> arrChi;

    public custom_adapter_dd(ArrayList<Chi> arrChi){
        this.arrChi = arrChi;
    }

    @Override
    public int getCount() {
        return arrChi.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View rowView = inflater.inflate(R.layout.custom_dudinh, parent, false);

        TextView tvIdTaiKhoan = (TextView) rowView.findViewById(R.id.tvIdDuDinh);
        TextView tienDuDinh = (TextView) rowView.findViewById(R.id.tvTienDuDinh);
        TextView id = (TextView) rowView.findViewById(R.id.tvIdDuDinh);
        TextView title = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tien = (TextView) rowView.findViewById(R.id.tvTien);
        Chi chi = arrChi.get(position);

        id.setText(chi.getId()+"");
        title.setText(chi.getMoTa());
        tvIdTaiKhoan.setText(chi.getIdTaiKhoan()+"");
        tienDuDinh.setText(chi.getSoTien()+"");
        DecimalFormat df = new DecimalFormat("###,###.## đ");
        tien.setText("= "+df.format(chi.getSoTien())+"");

        return rowView;
    }
}
