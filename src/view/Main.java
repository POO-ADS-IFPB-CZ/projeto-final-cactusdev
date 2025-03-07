package src.view;

import src.controller.ClienteController;
import src.controller.FornecedorController;
import src.controller.ProdutoController;
import src.dao.DaoWithFile;
import src.model.*;
import src.services.ClienteService;
import src.services.FornecedorService;
import src.services.adapters.GenerateWithValue;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FornecedorController fornecedorController = new FornecedorController();

        fornecedorController.addFornecedor(new Fornecedor("123456789", Tipo_fornecedor.JURIDICA, "Savio espalha lixo da silva", "Rua Augusta, 69", "88999564321"));

    }
}
