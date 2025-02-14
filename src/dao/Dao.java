package src.dao;

import src.controller.CustomError;

import java.io.IOException;
import java.util.Map;

public interface Dao <T, ID>{

    void addToList(ID codigo, T item) throws IOException, CustomError;
    void removeToList(ID codigo) throws IOException, CustomError;
    void updateItemOnList(ID codigo, T item) throws IOException, CustomError;
    void saveFile() throws IOException;
    Map<ID, T> getList();

}
