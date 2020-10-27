package com.codecool.simpleSQLApp.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    boolean add(T t);
    boolean edit(T t);
    boolean remove(long id);
    List<T> getAll() throws Exception;
    Optional<T> get(long id);

}
