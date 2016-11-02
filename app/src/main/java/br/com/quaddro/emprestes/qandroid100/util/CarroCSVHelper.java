package br.com.quaddro.emprestes.qandroid100.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import br.com.quaddro.emprestes.qandroid100.model.Carro;

public final class CarroCSVHelper {

    private static CarroCSVHelper HELPER;
    private Context context;

    private CarroCSVHelper(Context context) {
        super();
        this.context = context;
    }

    public static CarroCSVHelper getInstance(Context context) {
        if (HELPER == null) {
            HELPER = new CarroCSVHelper(context);
        }
        return HELPER;
    }

    public void salvar(CharSequence nome, CharSequence fabricante, CharSequence descricao, String arquivo)
            throws IOException {
        salvar(Carro.criar(nome, fabricante, descricao), arquivo);
    }

    public void salvar(Carro carro, String arquivo) throws IOException {
        try (FileOutputStream fos = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
             OutputStreamWriter w = new OutputStreamWriter(fos)) {
            String csv = carro.toCSV();

            // FIXME Gravar ao final do arquivo.

            w.write(csv);
            w.flush();
        }
    }

    public List<Carro> listarTodos(String arquivo) {
        List<Carro> list = new ArrayList<>();

        // try-with-resources (Closeable)
        try (FileInputStream fis = context.openFileInput(arquivo);
             InputStreamReader data = new InputStreamReader(fis)) {
            BufferedReader b = new BufferedReader(data);

            try {
                String linha;
                String[] campos;

                while (b.ready()) {
                    linha = b.readLine();
                    campos = linha.split(";");

                    list.add(Carro.criar(campos));

                    Log.d(arquivo, linha);
                }
            } finally {
                b.close();
            }
        } catch (IOException cause) {
            Log.e("CSV", "Problemas", cause);
        }

        return list;
    }
}
