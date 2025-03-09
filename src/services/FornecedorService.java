package src.services;

import src.controller.FornecedorController;
import src.model.Cliente;
import src.model.Fornecedor;
import src.model.Produto;
import src.model.Tipo_fornecedor;
import src.services.validators.ValidatorFornecedor;
import src.services.validators.ValidatorProduto;
import src.view.customErrors.Faill;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;

public class FornecedorService {
    private FornecedorController fornecedorController;
    public FornecedorService(){
        fornecedorController = new FornecedorController();
    }

    public boolean cadastrarFornecedor(String nome, String cnpj_cpf, String tipoFornecedor, String endereco,
                                       String telefone){
        try{
            Fornecedor fornecedor = ValidatorFornecedor.validarCamposCriarFornecedor(nome,cnpj_cpf,telefone,endereco,tipoFornecedor);

            fornecedorController.addFornecedor(fornecedor);
            return true;
        } catch (IllegalArgumentException e) {
            Faill.show(null, e.getMessage());
            return  false;
        }
    }

    public void mostrarFornecedoresNaTabela(DefaultTableModel model) {
        Collection<Fornecedor> fornecedores = fornecedorController.listarFornecedores();

        // Remove linhas antigas
        model.setRowCount(0);

        for (Fornecedor fornecedor : fornecedores) {
            model.addRow(new Object[]{
                    fornecedor.getCnpj(),
                    fornecedor.getNome(),
                    fornecedor.getTelefone(),
                    fornecedor.getEndereco(),
                    fornecedor.getDataCadastro(),
            });
        }
    }

    public Fornecedor getFornecedorPorCnpj(String cnpj){
        return fornecedorController.getFornecedorPorCodigo(cnpj);
    }
}
