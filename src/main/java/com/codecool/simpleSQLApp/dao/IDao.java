package com.codecool.simpleSQLApp.dao;

import java.util.List;

public interface IDao<T> {

    void add(T t);
    void edit(T t);
    void remove(T t);
    List<T> getAll() throws Exception;
    T get(int id);

}
