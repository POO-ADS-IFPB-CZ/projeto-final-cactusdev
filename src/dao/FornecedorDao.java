package src.dao;

import src.model.Fornecedor;

import java.io.*;
import java.util.*;

public class FornecedorDao {
    private final Map<String,Fornecedor> fornecedores;
    private File arquivo;

    public FornecedorDao() throws IOException, ClassNotFoundException {
        arquivo = new File("Fornecedores");
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.fornecedores = new HashMap<>(this.getFornecedores());
    }

    public boolean adicionarFornecedor(Fornecedor fornecedor) throws IOException
             {
        if(!fornecedores.containsKey(fornecedor.getCnpj())){
            this.fornecedores.put(fornecedor.getCnpj(),fornecedor);
            atualizarArquivo();
            return true;
        }
        return false;
    }

    public boolean removerFornecedor(String cnpj) throws IOException
             {
        if(fornecedores.containsKey(cnpj)){
            this.fornecedores.remove(cnpj);
            atualizarArquivo();
            return true;
        }
        return false;
    }

    public boolean atualizar(Fornecedor fornecedor) throws IOException {

        if (this.fornecedores.containsKey(fornecedor.getCnpj())) {

            fornecedores.put(fornecedor.getCnpj(), fornecedor); // Adiciona o fornecedor atualizado
            atualizarArquivo();
            return true; // Retorna true se o fornecedor foi atualizado com sucesso
        }
        return false; // Retorna false se o fornecedor não existia no conjunto
    }

    private void atualizarArquivo()
            throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivo))){
            out.writeObject(this.fornecedores);
        }
    }

    public Map<String,Fornecedor> getFornecedores() throws IOException,
            ClassNotFoundException {
        if(arquivo.length()==0){
            return new HashMap<>();
        }
        try(ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(arquivo))){
            return (Map<String, Fornecedor>) in.readObject();
        }
    }
}
