package br.com.quaddro.emprestes.qandroid100.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// TODO JavaDoc
public class MegaSena {

    private final Comparator<CharSequence> comparator;

    public MegaSena() {
        super();

        comparator = new Comparator<CharSequence>() {
            @Override
            public int compare(CharSequence p, CharSequence s) {
                return p.toString().compareTo(s.toString());
            }
        };
    }

    /**
     * Gera um sorteio.
     *
     * Regra:
     * 1. Gerar 6 números entre 1 e 60;
     * 2. Não permitir repetições.
     * 3. Ordenar os números.
     *
     * @return Números do sorteio
     */
    public CharSequence sortear() {
        // Integer[] sorteio = new Integer[6]; // Imutável.
        Integer[] sorteio = { 0, 0, 0, 0, 0, 0 }; // Array anônimo.
        int cont = 0, numero;

        // 1. Gerar 6 números entre 1 e 60;
        sorteio: do { // Loop por sentinela
            numero = (int) (1 + Math.random() * 60);

            for (int i = 0; i < sorteio.length; i++) {
                if (sorteio[i] == 0) {
                    sorteio[i] = numero;
                    cont++;
                    continue sorteio;
                } else if (sorteio[i] == numero) { // 2. Não permitir repetições.
                    continue sorteio;
                }
            }
        } while (cont != 6);

        // 3. Ordenar os números.
        Arrays.sort(sorteio);

        return String.format("%02d %02d %02d %02d %02d %02d", sorteio);
    }

    public ArrayList<CharSequence> sortear(int total) {
        final ArrayList<CharSequence> list = new ArrayList<>();
        CharSequence sorteio;

        while (list.size() < total) {
            sorteio = sortear();

            if (list.contains(sorteio)) {
                continue;
            }

            list.add(sorteio);
        }

        Collections.sort(list, comparator);

        return list;
    }
}
