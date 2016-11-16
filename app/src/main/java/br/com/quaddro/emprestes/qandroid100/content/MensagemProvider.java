package br.com.quaddro.emprestes.qandroid100.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import br.com.quaddro.emprestes.qandroid100.repository.Entity;
import br.com.quaddro.emprestes.qandroid100.util.MensagemSQLiteHelper;

public class MensagemProvider extends ContentProvider {

    private MensagemSQLiteHelper helper;

    @Override
    public boolean onCreate() {
        helper = MensagemSQLiteHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return helper.getReadableDatabase().query(Entity.Mensagem.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            db.insert(Entity.Mensagem.TABLE, null, values);
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            db.delete(Entity.Mensagem.TABLE, selection, selectionArgs);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            db.update(Entity.Mensagem.TABLE, values, selection, selectionArgs);
        }
        return 0;
    }
}
