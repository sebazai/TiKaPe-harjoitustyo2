/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harjoitustyo.database;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sebserge
 */

public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;
    
    void save(T object) throws SQLException;

    void delete(K key) throws SQLException;

}