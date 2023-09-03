package com.gingdev.services.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    public T createOrUpdate(T t) throws SQLException;

    public boolean detele(Integer id) throws SQLException;

    public List<T> all() throws SQLException;
}
