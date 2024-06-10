/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public abstract class DAO<T> {
    
    public abstract void insert(T obj) throws SQLException;
    public abstract T read(int id) throws SQLException;
    public abstract List<T> readAll() throws SQLException;
    public abstract void update(T entity) throws SQLException;
    public abstract void delete(int id) throws SQLException;
    
}
