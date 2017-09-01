package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import database.SQLHelper;
import model.Chi;

/**
 * Created by vuong on 24/10/2016.
 */

public class DAOChi {

    public void them(Chi chi, Context ct) {

        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idchi", chi.getId());
        values.put("sotienchi", chi.getSoTien());
        values.put("mucchi", chi.getMucChi());
        values.put("motachi", chi.getMoTa());
        values.put("taikhoanchi", chi.getTaiKhoanChi());
        values.put("thoigianchi", chi.getThoiGian());
        values.put("dachi", chi.getDaChi());
        values.put("idtaikhoanchi", chi.getIdTaiKhoan());

        db.insert("chi", null, values);

        Toast.makeText(ct, "Them thanh cong !", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Chi> getList(Context ct, int daChi) {
        ArrayList<Chi> dsDuDinh = new ArrayList<Chi>();
        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();

        Cursor cursor = db.query(false, "chi", null, "dachi = ?", new String[]{String.valueOf(daChi)}, null,
                null, null, null);

        while (cursor.moveToNext()) {
            Chi chi = new Chi();
            chi.setId(cursor.getLong(0));
            chi.setSoTien(cursor.getDouble(1));
            chi.setMucChi(cursor.getString(2));
            chi.setMoTa(cursor.getString(3));
            chi.setTaiKhoanChi(cursor.getString(4));
            chi.setThoiGian(cursor.getString(5));
            chi.setDaChi(cursor.getInt(6));
            chi.setIdTaiKhoan(cursor.getLong(7));
            dsDuDinh.add(chi);
        }
        return dsDuDinh;
    }

    public void deleteData(Context ct){

        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        db.execSQL("delete from chi");
        Toast.makeText(ct, "xoa du lieu Thu thanh cong !", Toast.LENGTH_SHORT).show();
    }

    public void delete(Context ct, long id){
        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        db.delete("chi","idchi = ?", new String[]{String.valueOf(id)});
        Toast.makeText(ct, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
    }

    public void luuDuDinh(Long id, Context ct){
        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dachi", 1);

        db.update("chi",values,"idchi = ?", new String[]{String.valueOf(id)});
    }
}
