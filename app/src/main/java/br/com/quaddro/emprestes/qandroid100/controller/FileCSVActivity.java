package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.model.Carro;
import br.com.quaddro.emprestes.qandroid100.util.CarroCSVHelper;

public class FileCSVActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FileCSVActivity.this);

                builder.setMessage("Ações")
                        .setCancelable(false)
                        .setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                Intent i = new Intent(FileCSVActivity.this, AlterarCarroActivity.class);

                                i.putExtra("campos", tv.getText());

                                startActivity(i);
                            }
                        })
                        .setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FileCSVActivity.this);

                                builder.setMessage("Confirmar?")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                                String[] campos = tv.getText().toString().split(" / ");
                                                try {
                                                    CarroCSVHelper.getInstance(getApplicationContext())
                                                            .apagar(campos[0], campos[1], "carros.csv");

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
        List<Carro> list;

        try {
            list = CarroCSVHelper.getInstance(this).listarTodos("carros.csv");
            setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        } catch (FileNotFoundException cause) {
            Toast.makeText(this, "Lista vazia!", Toast.LENGTH_SHORT).show();
            Log.e("CSV", "Lista vazia!", cause);
        } catch (IOException cause) {
            Log.e("CSV", "Problemas", cause);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, getResources().getString(R.string.carro_criar));
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case 0: // Criar
                Intent i = new Intent(this, NovoCarroActivity.class);
                startActivity(i);
                break;
        }

        return super.onMenuItemSelected(featureId, item);
    }
}
