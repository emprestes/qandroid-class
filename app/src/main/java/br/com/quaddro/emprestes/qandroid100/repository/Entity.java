package br.com.quaddro.emprestes.qandroid100.repository;

import android.provider.BaseColumns;

public interface Entity {

    interface Mensagem {
        String TABLE = "mensagem";

        interface Columns extends BaseColumns {
            String TITULO = "titulo";
            String CORPO = "corpo";
        }

        interface SQL {
            String CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(250), %s TEXT);",
                    TABLE, Columns._ID, Columns.TITULO, Columns.CORPO);
            String DELETE = String.format("%s = ?", Columns._ID);
            String UPDATE = String.format("%s = ?", Columns._ID);

            interface Select {
                String[] ALL = { Columns.TITULO, Columns.CORPO, Columns._ID };
                String WHERE_ID = String.format("%s = ?", Columns._ID);
            }
        }
    }

    interface  Table2 {
        String TABLE = "table2";
    }
}
