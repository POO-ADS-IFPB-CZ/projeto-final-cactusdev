package src.controller;

import src.dao.Dao;
import src.model.Venda;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

import java.io.IOException;
import java.util.Collection;

public final class VendaController {

    private final Dao<Venda, String> vendaDao;

    public VendaController(Dao<Venda, String> dao) {
        this.vendaDao = dao;
    }

    public void adicionarVenda(Venda venda) {
        try {
            vendaDao.addToList(venda.getCodigo(), venda);
            Success.show(null, "Venda registrada com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Venda já existe.");
        }
    }

    public void removerVenda(String codigo) {
        try {
            vendaDao.removeToList(codigo);
            Success.show(null, "Venda removida com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Venda não encontrada.");
        }
    }

    public void atualizarVenda(Venda venda) {
        try {
            vendaDao.updateItemOnList(venda.getCodigo(), venda);
            Success.show(null, "Venda atualizada com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Venda não encontrada.");
        }
    }

    public Collection<Venda> listarVendas() {
        return vendaDao.getList().values();
    }

    public void salvarVendas() {
        if (!vendaDao.getList().isEmpty()) {
            try {
                vendaDao.saveFile();
                Success.show(null, "Vendas salvas com sucesso.");
            } catch (IOException e) {
                Faill.show(null, "Erro interno ao salvar as vendas: " + e.getMessage());
            }
        }
    }
}
