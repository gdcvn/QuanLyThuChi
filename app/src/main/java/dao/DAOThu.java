package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import database.SQLHelper;
import model.Thu;

/**
 * Created by vuong on 24/10/2016.
 */

public class DAOThu {

    public void them(Thu thu, Context ct) {

        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idthu", thu.getId());
        values.put("sotienthu", thu.getSoTien());
        values.put("mucthu", thu.getMucThu());
        values.put("motathu", thu.getMoTa());
        values.put("taikhoanthu", thu.getTaiKhoanThu());
        values.put("thoigianthu", thu.getThoiGian());

        db.insert("thu", null, values);

        Toast.makeText(ct, "Them thanh cong !", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(Context ct){

        SQLHelper sqlHelper = new SQLHelper(ct);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        db.execSQL("delete from thu");
        Toast.makeText(ct, "xoa du lieu Thu thanh cong !", Toast.LENGTH_SHORT).show();
    }
}
