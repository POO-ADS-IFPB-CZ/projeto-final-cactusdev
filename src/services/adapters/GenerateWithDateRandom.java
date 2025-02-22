package src.services.adapters;

import src.model.interfaces.CodigoGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateWithDateRandom implements CodigoGenerator {
    @Override
    public String gerarCodigo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyHHmmssSSS") ;
        return  LocalDateTime.now().format(formatter) + new Random().nextInt(100);
    }
}
