package src.controller;

import src.dao.Dao;
import src.dao.DaoWithFile;
import src.model.Fornecedor;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

import java.io.IOException;
import java.util.Collection;

public final class FornecedorController {

    private final Dao<Fornecedor, String> fornecedorDao;

    public FornecedorController() {

        try {
            this.fornecedorDao = new DaoWithFile<Fornecedor, String>("Fornecedores");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFornecedor(Fornecedor fornecedor) {
        try {
            fornecedorDao.addToList(fornecedor.getCnpj(), fornecedor);
            Success.show(null, "Fornecedor adicionado com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Fornecedor já existe.");
        }
    }

    public void removeFornecedor(String cnpj) {
        try {
            fornecedorDao.removeToList(cnpj);
            Success.show(null, "Fornecedor removido com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Fornecedor não encontrado.");
        }
    }

    public void atualizarFornecedor(Fornecedor fornecedor) {
        try {
            fornecedorDao.updateItemOnList(fornecedor.getCnpj(), fornecedor);
            Success.show(null, "Fornecedor atualizado com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Fornecedor não encontrado.");
        }
    }

    public Collection<Fornecedor> listarFornecedores() {
        return fornecedorDao.getList().values();
    }

    public void salvarFornecedores() {
        if (!fornecedorDao.getList().isEmpty()) {
            try {
                fornecedorDao.saveFile();
                Success.show(null, "Fornecedores salvos com sucesso.");
            } catch (IOException e) {
                Faill.show(null, "Erro interno ao salvar os fornecedores: " + e.getMessage());
            }
        }
    }

    public Fornecedor getFornecedorPorCodigo(String codigo){
        return fornecedorDao.getList().get(codigo);
    }
}
