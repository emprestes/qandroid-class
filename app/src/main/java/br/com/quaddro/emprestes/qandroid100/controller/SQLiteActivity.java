package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.repository.Entity;
import br.com.quaddro.emprestes.qandroid100.util.MensagemSQLiteHelper;

public class SQLiteActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SQLiteActivity.this);

                builder.setMessage("Ações")
                        .setCancelable(false)
                        .setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(SQLiteActivity.this, AlterarMensagemActivity.class);

                                i.putExtra("id", id);

                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SQLiteActivity.this);

                                builder.setMessage("Confirmar?")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                try {
                                                    MensagemSQLiteHelper.getInstance(getApplicationContext())
                                                            .apagar(id);

                                                    onResume();
                                                } catch (Exception cause) {
                                                    Log.e("carros.csv", "PROBLEMAS AO APAGAR", cause);
                                                }
                                            }
                                        });

                                AlertDialog alert = builder.create();

                                alert.show();
                            }
                        });

                AlertDialog alert = builder.create();

                alert.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor c = MensagemSQLiteHelper.getInstance(this).listarTodas();

        setListAdapter(new CursorAdapter(this, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                LayoutInflater li = LayoutInflater.from(context);
                View v = li.inflate(R.layout.mensagem_view, null);
                WebView wv = (WebView) v.findViewById(R.id.web_title);
                wv.getSettings().setDefaultTextEncodingName("UTF-8");
                wv = (WebView) v.findViewById(R.id.web_body);
                wv.getSettings().setDefaultTextEncodingName("UTF-8");
                return v;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                WebView wv;
                String data;
                int index;

                index = cursor.getColumnIndex(Entity.Mensagem.Columns.TITULO);
                data = cursor.getString(index);
                wv = (WebView) view.findViewById(R.id.web_title);
                wv.loadData(String.format("<html><body>%s</body></html>", data), "text/html; charset=utf-8", "UTF-8");

                index = cursor.getColumnIndex(Entity.Mensagem.Columns.CORPO);
                data = cursor.getString(index);
                wv = (WebView) view.findViewById(R.id.web_body);
                wv.loadData(String.format("<html><body>%s</body></html>", data), "text/html; charset=utf-8", "UTF-8");
            }
        });

//        setListAdapter(new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2,
//                c,
//                Entity.Mensagem.SQL.Select.ALL,
//                new int[] {
//                        android.R.id.text1,
//                        android.R.id.text2
//                },
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
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
