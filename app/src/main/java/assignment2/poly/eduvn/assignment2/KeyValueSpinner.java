package assignment2.poly.eduvn.assignment2;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.TaiKhoan;

/**
 * Created by vuong on 24/10/2016.
 */

public class KeyValueSpinner implements SpinnerAdapter {
    Context context;
    ArrayList<TaiKhoan> listTaiKhoan;

    public KeyValueSpinner(Context context ,ArrayList<TaiKhoan> listTaiKhoan){
        this.context =context;
        this.listTaiKhoan = listTaiKhoan;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listTaiKhoan.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listTaiKhoan.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    //Note:-Create this two method getIDFromIndex and getIndexByID
    public Long getIDFromIndex(int Index) {
        return    listTaiKhoan.get(Index).getId();
    }

    public int getIndexByID(int ID) {
        for(int i=0;i < listTaiKhoan.size();i++){
            if(listTaiKhoan.get(i).getId() == ID){
            return i;
        }
    }
    return -1;
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textview = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textview.setText(listTaiKhoan.get(position).getTenTaiKhoan());

        return textview;
    }

    @Override
    public int getViewTypeCount() {
        return android.R.layout.simple_spinner_item;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textview = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textview.setText(listTaiKhoan.get(position).getTenTaiKhoan());

        return textview;
    }

}
