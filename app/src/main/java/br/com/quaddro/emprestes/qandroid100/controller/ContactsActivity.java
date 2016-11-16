package br.com.quaddro.emprestes.qandroid100.controller;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

import br.com.quaddro.emprestes.qandroid100.api.QuaddroActivity;

public class ContactsActivity extends QuaddroActivity {

    private String[] from = { ContactsContract.Contacts.DISPLAY_NAME };
    private int[] to = { android.R.id.text1 };
    private SimpleCursorAdapter adapter;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(android.R.layout.list_content);

        list = getViewById(android.R.id.list);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                from,
                to,
                0);

        int f = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (f != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 78);
        }

        f = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (f == PackageManager.PERMISSION_GRANTED) {
            getSupportLoaderManager().initLoader(78, new Bundle(), contacts);
        }

        list.setAdapter(adapter);
    }

    private LoaderManager.LoaderCallbacks<Cursor> contacts =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    // content://com.android.contacts/contacts/1
                    CursorLoader cl = new CursorLoader(ContactsActivity.this,
                            ContactsContract.Contacts.CONTENT_URI,
                            new String[] {ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME},
                            null,  // where
                            null,  // whereArgs
                            null); // orderBy

                    return cl;
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    adapter.swapCursor(data);
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    adapter.swapCursor(null);
                }
            };
}
