package src.services.adapters.codigo_generator;

import src.model.interfaces.CodigoGenerator;

public class GenerateWithValue implements CodigoGenerator {
    private final String value;
    public GenerateWithValue(String value){
        this.value = value;
    }
    @Override
    public String gerarCodigo() {
        return value;
    }
}
