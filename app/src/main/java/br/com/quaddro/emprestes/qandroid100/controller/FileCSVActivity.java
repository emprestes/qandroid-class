package br.com.quaddro.emprestes.qandroid100.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.quaddro.emprestes.qandroid100.R;
import br.com.quaddro.emprestes.qandroid100.model.Carro;
import br.com.quaddro.emprestes.qandroid100.util.CarroCSVHelper;

public class FileCSVActivity extends ListActivity {

    private List<Carro> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (list.isEmpty()) {
            list.addAll(CarroCSVHelper.getInstance(this).listarTodos("carros.csv"));
        }

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
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
