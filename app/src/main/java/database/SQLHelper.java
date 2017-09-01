package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vuong_it on 23/09/2016.
 */

public class SQLHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "ccqwe.db";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableTaiKhoan = "CREATE TABLE `taikhoan` (\n" +
                "\t`id`\tINTEGER NOT NULL,\n" +
                "\t`tentaikhoan`\tTEXT NOT NULL,\n" +
                "\t`loaitaikhoan`\tTEXT NOT NULL,\n" +
                "\t`sotien`\tNUMERIC NOT NULL,\n" +
                "\tPRIMARY KEY(`id`)\n" +
                ");";

        String tableThu = "CREATE TABLE `thu` (\n" +
                "\t`idthu`\tINTEGER NOT NULL,\n" +
                "\t`sotienthu`\tNUMERIC NOT NULL,\n" +
                "\t`mucthu`\tTEXT NOT NULL,\n" +
                "\t`motathu`\tTEXT NOT NULL,\n" +
                "\t`taikhoanthu`\tTEXT NOT NULL,\n" +
                "\t`thoigianthu`\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(`idthu`)\n" +
                ");";

        String tableChi = "CREATE TABLE `chi` (\n" +
                "\t`idchi`\tINTEGER NOT NULL,\n" +
                "\t`sotienchi`\tNUMERIC NOT NULL,\n" +
                "\t`mucchi`\tTEXT NOT NULL,\n" +
                "\t`motachi`\tTEXT NOT NULL,\n" +
                "\t`taikhoanchi`\tTEXT NOT NULL,\n" +
                "\t`thoigianchi`\tTEXT NOT NULL,\n" +
                "\t`dachi`\tINTEGER NOT NULL,\n" +
                "\t`idtaikhoanchi`\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(`idchi`)\n" +
                ");";
        sqLiteDatabase.execSQL(tableTaiKhoan);
        sqLiteDatabase.execSQL(tableThu);
        sqLiteDatabase.execSQL(tableChi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL("drop if exists taikhoan");
            sqLiteDatabase.execSQL("drop if exists thu");
            sqLiteDatabase.execSQL("drop if exists chi");

            onCreate(sqLiteDatabase);
        }
    }
}