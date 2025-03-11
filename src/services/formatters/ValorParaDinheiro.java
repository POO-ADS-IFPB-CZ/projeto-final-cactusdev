package src.services.formatters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class ValorParaDinheiro {

    // Método original que usa o formato de moeda (com símbolo)
    public static String converter(Double valor, String language, String country){
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale(language, country));
        return formatoMoeda.format(valor);
    }

    // Novo método que formata o valor sem o símbolo da moeda
    public static String converterSemMoeda(Double valor) {
        // Cria o DecimalFormatSymbols para definir o ponto como separador decimal
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.'); // Define o ponto como separador decimal
        simbolos.setGroupingSeparator(','); // Define a vírgula como separador de milhar (opcional)

        // Cria o formato para número com duas casas decimais, usando ponto como separador decimal
        DecimalFormat formato = new DecimalFormat("#,##0.00", simbolos);
        return formato.format(valor);
    }

    public static String converterParaDouble(String valorComVirgula) {
        // Remover vírgulas do valor (separador de milhar)
        valorComVirgula = valorComVirgula.replace(",", "");
        // Converter para Double
        return valorComVirgula;
    }
}
