package src.controller;

import src.dao.Dao;
import src.model.Pedido;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

import java.io.IOException;
import java.util.Collection;

public final class PedidoController {

    private final Dao<Pedido, String> pedidoDao;

    public PedidoController(Dao<Pedido, String> dao) {
        this.pedidoDao = dao;
    }

    public void addPedido(Pedido pedido) {
        try {
            pedidoDao.addToList(pedido.getCodigo(), pedido);
            Success.show(null, "Pedido adicionado com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Pedido já existe.");
        }
    }

    public void removePedido(String codigo) {
        try {
            pedidoDao.removeToList(codigo);
            Success.show(null, "Pedido removido com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Pedido não encontrado.");
        }
    }

    public void atualizarPedido(Pedido pedido) {
        try {
            pedidoDao.updateItemOnList(pedido.getCodigo(), pedido);
            Success.show(null, "Pedido atualizado com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Pedido não encontrado.");
        }
    }

    public Collection<Pedido> listarPedidos() {
        return pedidoDao.getList().values();
    }

    public void salvarPedidos() {
        if (!pedidoDao.getList().isEmpty()) {
            try {
                pedidoDao.saveFile();
                Success.show(null, "Pedidos salvos com sucesso.");
            } catch (IOException e) {
                Faill.show(null, "Erro interno ao salvar os pedidos: " + e.getMessage());
            }
        }
    }
}
