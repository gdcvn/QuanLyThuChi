package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import database.SQLHelper;
import model.TaiKhoan;

public class DAOTaiKhoan {

	public void them(TaiKhoan taiKhoan, Context ct) {

		SQLHelper sqlHelper = new SQLHelper(ct);
		SQLiteDatabase db = sqlHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("id", taiKhoan.getId());
		values.put("tentaikhoan", taiKhoan.getTenTaiKhoan());
		values.put("loaitaikhoan", taiKhoan.getLoaiTaiKhoan());
		values.put("sotien", taiKhoan.getSoTien());

		db.insert("taikhoan", null, values);

		Toast.makeText(ct, "Them thanh cong !", Toast.LENGTH_SHORT).show();
	}

	public ArrayList<TaiKhoan> getList(Context ct) {
		ArrayList<TaiKhoan> dsTaiKhoan = new ArrayList<TaiKhoan>();
		SQLHelper sqlHelper = new SQLHelper(ct);
		SQLiteDatabase db = sqlHelper.getReadableDatabase();

		Cursor cursor = db.query(false, "taikhoan", null, null, null, null,
				null, null, null);

		while (cursor.moveToNext()) {
			TaiKhoan taiKhoan = new TaiKhoan();
			taiKhoan.setId(cursor.getLong(0));
			taiKhoan.setTenTaiKhoan(cursor.getString(1));
			taiKhoan.setLoaiTaiKhoan(cursor.getString(2));
			taiKhoan.setSoTien(cursor.getDouble(3));
			dsTaiKhoan.add(taiKhoan);
		}
		return dsTaiKhoan;
	}

	public int getSum(Context ct){
		SQLHelper sqlHelper = new SQLHelper(ct);
		SQLiteDatabase db = sqlHelper.getReadableDatabase();

		Cursor cur = db.rawQuery("SELECT SUM(sotien) FROM taikhoan", null);
		if(cur.moveToFirst())
		{
			return cur.getInt(0);
		}

//				db.execSQL("delete from taikhoan");

		return 0;
	}

	public void delete(Context ct, long id){
		SQLHelper sqlHelper = new SQLHelper(ct);
		SQLiteDatabase db = sqlHelper.getWritableDatabase();

		db.delete("taikhoan","id = ?", new String[]{String.valueOf(id)});
		Toast.makeText(ct, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
	}

	public double getSoTien(Long id, Context ct){

		SQLHelper sqlHelper = new SQLHelper(ct);
		SQLiteDatabase db = sqlHelper.getReadableDatabase();

		Cursor cur = db.rawQuery("SELECT sotien FROM taikhoan WHERE id='"+id+"'", null);
		if(cur.moveToFirst())
		{
			return cur.getInt(0);
		}
		return 0;
	}

	public void update(Long id, double tien, Context ct){
		SQLHelper sqlHelper = new SQLHelper(ct);
		SQLiteDatabase db = sqlHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("sotien", tien);

		db.update("taikhoan",values,"id = ?", new String[]{String.valueOf(id)});
	}
}
