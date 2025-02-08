package src.dao;

import src.model.Produto;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProdutoDao {

    private Map<String, Produto> produtos;
    private File arquivo;

    public ProdutoDao() throws IOException, ClassNotFoundException {
        arquivo = new File("Produtos");
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.produtos = new HashMap<>(getProdutos());
    }

    public boolean adicionarProduto(Produto produto) throws IOException {
        if (produtos.containsKey(produto.getCodigo())){
            return false;
        }
        produtos.put(produto.getCodigo(), produto);
        atualizarArquivo();
        return false;
    }

    public boolean removerProduto(String cod_produto) throws IOException{

        if (produtos.containsKey(cod_produto)){
            produtos.remove(cod_produto);
            atualizarArquivo();
            return true;
        }
        return false;
    }

    public boolean atualizarProduto(Produto produto) throws IOException {

        if(produtos.containsKey(produto.getCodigo())){
            produtos.put(produto.getCodigo(), produto);
            atualizarArquivo();
        }
        return false;
    }

    private void atualizarArquivo()
            throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivo))){
            out.writeObject(produtos);
        }
    }

    public Map<String,Produto> getProdutos() throws IOException,
            ClassNotFoundException {
        if(arquivo.length()==0){
            return new HashMap<>();
        }
        try(ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(arquivo))){
            return (Map<String,Produto>) in.readObject();
        }
    }

}
