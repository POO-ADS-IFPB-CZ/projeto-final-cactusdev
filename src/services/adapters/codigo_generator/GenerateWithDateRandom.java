package src.services.adapters.codigo_generator;

import src.model.interfaces.CodigoGenerator;

public class GenerateWithDateRandom implements CodigoGenerator {
    private final String saveCode;
    public GenerateWithDateRandom(String saveCode){
        this.saveCode = saveCode;
    }
    @Override
    public String gerarCodigo() {
        return this.saveCode;
    }
}
