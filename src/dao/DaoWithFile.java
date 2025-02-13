package src.dao;

import javax.swing.*;
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
        this.list = getItensList();
    }

    @Override
    public void addToList(ID codigo, T item) throws IOException, Error{
        if (list.containsKey(codigo)){
            throw new Error();
        }
        list.put(codigo, item);
        saveFile();
    }

    @Override
    public void removeToList(ID codigo) throws IOException, Error{
        if (list.containsKey(codigo)){
            list.remove(codigo);
            saveFile();
            return;
        }
        throw new Error();
    }

    @Override
    public void updateItemOnList(ID codigo, T item) throws IOException, Error{
        if(list.containsKey(codigo)){
            list.put(codigo, item);
            saveFile();

        }
        throw new Error();
    }

    private Map<ID, T> getItensList() throws IOException, ClassNotFoundException {
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

    @Override
    public Map<ID, T> getList() {
        return list;
    }

}
