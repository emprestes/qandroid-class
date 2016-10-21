package br.com.quaddro.emprestes.qandroid100.tablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.quaddro.emprestes.qandroid100.domain.model.MegaSena;

public class MainActivity extends AppCompatActivity {

    private TextView view;
    private MegaSena model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (TextView) findViewById(R.id.sorteio);
        model = new MegaSena();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Oi", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        sortear(view);
    }

    public void sortear(View view) {
        sortear(this.view);
    }

    public final void sortear(TextView view) {
        CharSequence sorteio;

        sorteio = model.sortear();
        view.setText(sorteio);
    }
}
