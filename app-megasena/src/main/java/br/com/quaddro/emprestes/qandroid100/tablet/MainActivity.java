package br.com.quaddro.emprestes.qandroid100.tablet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.quaddro.emprestes.qandroid100.domain.model.MegaSena;
import br.com.quaddro.emprestes.qandroid100.tablet.api.OnSeekBarChangeAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String MEGASENA_SORTEIO = "megasena.sorteio";
    public static final String SORTEIOS = "sorteios";

    private TextView tvView;
    private SeekBar sbView;
    private ListView lvView;
    private MegaSena model;

    private Resources r;

    private ArrayList<CharSequence> sorteios;

    private SeekBar.OnSeekBarChangeListener listener = new OnSeekBarChangeAdapter() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            tvView.setText(String.valueOf(progress));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvView = (TextView) findViewById(R.id.tv_jogos);
        sbView = (SeekBar) findViewById(R.id.sb_jogos);
        lvView = (ListView) findViewById(android.R.id.list);
        model = new MegaSena();
        r = getResources();

        if (savedInstanceState != null) {
            sorteios = savedInstanceState.getCharSequenceArrayList(SORTEIOS);
        } else {
            sorteios = new ArrayList<>();
        }

        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        sbView.setOnSeekBarChangeListener(listener);
        lvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClipboardManager clipboard;
                ClipData clip;
                TextView tv;

                tv = (TextView) view;
                clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clip = ClipData.newPlainText(MEGASENA_SORTEIO, tv.getText());

                clipboard.setPrimaryClip(clip);

                Toast.makeText(getApplicationContext(),
                        r.getString(R.string.copiado),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequenceArrayList(SORTEIOS, sorteios);
    }

    public void sortear(View view) {
        sorteios = model.sortear(sbView.getProgress());

        updateView();
    }

    private void updateView() {
        lvView.setAdapter(new ArrayAdapter<>(this,
                R.layout.jogo_view,
                android.R.id.text1,
                sorteios));
    }
}
