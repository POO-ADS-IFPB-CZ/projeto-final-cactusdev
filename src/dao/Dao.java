package src.dao;

import java.io.IOException;
import java.util.Map;

public interface Dao <T, ID>{

    void addToList(ID codigo, T item) throws IOException;
    boolean removeToList(ID codigo) throws IOException;
    boolean updateItemOnList(ID codigo, T item) throws IOException;
    Map<ID, T> getItensList() throws IOException,
            ClassNotFoundException;
    void saveFile() throws IOException;

}
