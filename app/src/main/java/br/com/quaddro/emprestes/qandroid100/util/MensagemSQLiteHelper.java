package br.com.quaddro.emprestes.qandroid100.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.quaddro.emprestes.qandroid100.model.Mensagem;
import br.com.quaddro.emprestes.qandroid100.repository.DB;

import static br.com.quaddro.emprestes.qandroid100.repository.Entity.Mensagem.TABLE;
import static br.com.quaddro.emprestes.qandroid100.repository.Entity.Mensagem.Columns;
import static br.com.quaddro.emprestes.qandroid100.repository.Entity.Mensagem.SQL;

public class MensagemSQLiteHelper extends SQLiteOpenHelper {

    private static MensagemSQLiteHelper HELPER;

    private MensagemSQLiteHelper(Context context) {
        super(context, DB.NAME, null, DB.VERSION);
    }

    public static MensagemSQLiteHelper getInstance(Context context) {
        if (HELPER == null) {
            HELPER = new MensagemSQLiteHelper(context);
        }

        return HELPER;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(CharSequence titulo, CharSequence corpo) {
        inserir(Mensagem.criar(titulo, corpo));
    }

    public void inserir(Mensagem model) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put(Columns.TITULO, model.getTitulo().toString());
            values.put(Columns.CORPO, model.getCorpo().toString());

            db.insert(TABLE, null, values);
        }
    }

    public void apagar(Mensagem model) {
        if (model != null) {
            apagar(model.getId());
        }
    }

    public void apagar(Integer id) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.delete(TABLE, SQL.DELETE, new String[]{id.toString()});
        }
    }

    public void apagarTodas() {
        try (SQLiteDatabase db = getWritableDatabase()) {
            db.delete(TABLE, null, null); // DELETE FROM mensagem;
        }
    }

    public void atualizar(Mensagem model) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put(Columns.TITULO, model.getTitulo().toString());
            values.put(Columns.CORPO, model.getCorpo().toString());

            db.update(TABLE, values, SQL.UPDATE, new String[] { model.getId().toString() });
        }
    }

    public Cursor listarTodas() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE,
                SQL.Select.ALL, // columns
                null,           // where
                null,           // whereArgs
                null,           // groupBy
                null,           // having
                null,           // orderBy
                null);          // limit
    }
}
