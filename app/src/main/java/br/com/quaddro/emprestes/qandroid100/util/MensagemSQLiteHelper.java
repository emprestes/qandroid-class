package br.com.quaddro.emprestes.qandroid100.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MensagemSQLiteHelper extends SQLiteOpenHelper {

    public MensagemSQLiteHelper(Context context) {
        super(context, "mensagem.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
