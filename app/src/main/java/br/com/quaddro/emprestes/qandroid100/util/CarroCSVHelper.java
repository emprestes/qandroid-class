package br.com.quaddro.emprestes.qandroid100.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import br.com.quaddro.emprestes.qandroid100.exception.CarroEqualException;
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

    public void inserir(CharSequence nome, CharSequence fabricante, CharSequence descricao, String arquivo)
            throws IOException {
        inserir(Carro.criar(nome, fabricante, descricao), arquivo);
    }

    public void inserir(Carro carro, String arquivo) throws IOException {
        File f = new File(context.getFilesDir(), arquivo);

        try (RandomAccessFile rsf = new RandomAccessFile(f, "rw")) {
            String csv = carro.toCSV();

            rsf.seek(rsf.length());

            rsf.writeChars(csv.concat("\n"));
        }
    }

    public void alterar(CharSequence nome, CharSequence fabricante,
                        CharSequence nomeNovo, CharSequence fabricanteNovo, String arquivo)
            throws IOException, CarroEqualException {
        alterar(Carro.criar(nome, fabricante), Carro.criar(nomeNovo, fabricanteNovo), arquivo);
    }

    public void alterar(Carro carro, Carro carroNovo, String arquivo)
            throws IOException, CarroEqualException {
        if (carro.equals(carroNovo)) {
            throw new CarroEqualException();
        }

        StringBuilder content = new StringBuilder();

        try (FileInputStream fis = context.openFileInput(arquivo);
             InputStreamReader reader = new InputStreamReader(fis);
             BufferedReader data = new BufferedReader(reader)) {
            String row;
            String a = carro.toCSV();

            while (data.ready()) {
                row = data.readLine();

                if (row.contains(a)) {
                    // TODO Persistir a descrição.
                    row = carroNovo.toCSV().concat(" ");
                }

                content.append(row).append('\n');
            }
        }

        try (FileOutputStream fos = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos)) {
            writer.write(content.toString());
            writer.flush();
        }
    }

    public void apagar(CharSequence nome, CharSequence fabricante, String arquivo)
            throws IOException {
        apagar(Carro.criar(nome, fabricante), arquivo);
    }

    public void apagar(Carro carro, String arquivo) throws IOException {
        StringBuilder content = new StringBuilder();

        try (FileInputStream fis = context.openFileInput(arquivo);
             InputStreamReader reader = new InputStreamReader(fis);
             BufferedReader data = new BufferedReader(reader)) {
            String row;
            String a = carro.toCSV();

            while (data.ready()) {
                row = data.readLine();

                if (row.contains(a)) {
                    continue;
                }

                content.append(row).append('\n');
            }
        }

        try (FileOutputStream fos = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos)) {
            writer.write(content.toString());
            writer.flush();
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

                    if (!linha.contains(";")) {
                        continue;
                    }

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
