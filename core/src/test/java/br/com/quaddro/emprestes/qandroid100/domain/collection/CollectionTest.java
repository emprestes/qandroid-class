package br.com.quaddro.emprestes.qandroid100.domain.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CollectionTest {

    @Test
    public void collectionTest() {

    }

    @Test
    public void listTest() {
        List<Pessoa> list = new ArrayList<>();
        Pessoa p = null;
        int ns, nn, ni;

        for (int i = 0; i < 100; i++) {
            nn = (int) (1 + Math.random() * 99);
            ns = (int) (1 + Math.random() * 99);
            ni = (int) (1 + Math.random() * 99);
            p = new Pessoa("Nome" + nn, "S" + ns, ni);
            list.add(p);
        }

        Collections.sort(list, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p1, Pessoa p2) {
                int comp = p1.getIdade().compareTo(p2.getIdade());
                comp = comp == 0 ? p1.getSobrenome().compareToIgnoreCase(p2.getSobrenome()) : comp;
                comp = comp == 0 ? p1.getNome().compareToIgnoreCase(p2.getNome()) : comp;

                return comp;
            }
        });

        for (Pessoa i : list) {
            System.out.println(i);
        }
    }

    @Test
    public void setTest() {
        Set<Pessoa> set = new TreeSet<>();
        Pessoa p = null;

        for (int i = 0; i < 100; i++) {
            p = (i % 2 == 0 ? new Pessoa("Nome" + i, "S" + i, i) : p);
            set.add(p);
        }

        Iterator<Pessoa> i = set.iterator();

        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    @Test
    public void mapTest() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("ZZZ", 23);
        map.put("DDD", 89);
        map.put("UUU", 67);
        map.put("TTT", 12);
        map.put("PPP", 34);
        map.put("AAA", 45);

        for (String key : map.keySet()) {
            System.out.printf("%s: %s%n", key, map.get(key));
        }
    }
}
