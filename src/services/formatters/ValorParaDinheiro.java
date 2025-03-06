package src.services.formatters;

import java.text.NumberFormat;
import java.util.Locale;

public class ValorParaDinheiro {
    public static String converter(Double valor, String language, String country){
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale(language, country));

        return formatoMoeda.format(valor);
    }
}
