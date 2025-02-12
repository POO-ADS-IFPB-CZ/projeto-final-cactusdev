package src.dao;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DaoWithFile<T, ID> implements Dao<T,ID>{
    private final File arquivo;
    private final Map<ID, T> list;

    public DaoWithFile(String name_file) throws IOException, ClassNotFoundException {
        arquivo = new File(name_file);

        if(!arquivo.exists()){
            arquivo.createNewFile();
        }

        Map<ID, T> itensLidos = getItensList();
        this.list = (itensLidos != null) ? itensLidos : new HashMap<>();
    }

    @Override
    public void addToList(ID codigo, T item) throws IOException{
        if (list.containsKey(codigo)){
            return;
        }
        list.put(codigo, item);
        saveFile();
    }

    @Override
    public boolean removeToList(ID codigo) throws IOException{
        if (list.containsKey(codigo)){
            list.remove(codigo);
            saveFile();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateItemOnList(ID codigo, T item) throws IOException{
        if(list.containsKey(codigo)){
            list.put(codigo, item);
            saveFile();
            return  true;
        }
        return false;
    }

    @Override
    public Map<ID, T> getItensList() throws IOException, ClassNotFoundException {
        if (arquivo.length() == 0) {
            return new HashMap<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = in.readObject();
            if (obj instanceof Map) {
                return (Map<ID, T>) obj;
            }
            return new HashMap<>();
        } catch (EOFException e) {
            return new HashMap<>();
        }
    }

    @Override
    public void saveFile() throws IOException{
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivo))){
            out.writeObject(list);
        }
    }
}
