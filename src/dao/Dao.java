package src.dao;

import java.io.IOException;
import java.util.Map;

public interface Dao <T, ID>{

    void addToList(ID codigo, T item) throws IOException, Error;
    void removeToList(ID codigo) throws IOException, Error;
    void updateItemOnList(ID codigo, T item) throws IOException, Error;
    void saveFile() throws IOException;
    Map<ID, T> getList();

}
