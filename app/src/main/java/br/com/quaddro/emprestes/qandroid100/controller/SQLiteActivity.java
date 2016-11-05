package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.repository.Entity;
import br.com.quaddro.emprestes.qandroid100.util.MensagemSQLiteHelper;

public class SQLiteActivity extends ListActivity {

    @Override
    protected void onResume() {
        super.onResume();

        Cursor c = MensagemSQLiteHelper.getInstance(this).listarTodas();

        setListAdapter(new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                c,
                Entity.Mensagem.SQL.Select.ALL,
                new int[] {
                        android.R.id.text1,
                        android.R.id.text2
                },
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, getResources().getString(R.string.mensagem_criar));
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case 0: // Criar
                Intent i = new Intent(this, NovaMensagemActivity.class);
                startActivity(i);
                break;
        }

        return super.onMenuItemSelected(featureId, item);
    }
}
