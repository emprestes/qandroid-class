package br.com.quaddro.emprestes.qandroid100.model;

/**
 * Minha classe pra dizer oi.
 *
 * @author Prestes, E. M.
 * @since Outubro de 2016
 * @version 1.0
 *
 * @see String
 */
public class Oi {

    /**
     * Comportamento para dizer seu nome.
     *
     * @param name Nome informado.
     * @return Mensagem dita.
     */
    public String dizer(String name) {
        if (name != null && !name.isEmpty()) {
            return "Oi, " + name;
        } else {
            return "Favor, diga seu nome";
        }
    }
}
