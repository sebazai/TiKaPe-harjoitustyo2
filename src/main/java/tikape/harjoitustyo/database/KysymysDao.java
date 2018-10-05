/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harjoitustyo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.harjoitustyo.domain.Kysymys;

/**
 *
 * @author sebserge
 */
public class KysymysDao implements Dao<Kysymys, Integer>  {
       private Connection connection;

    public KysymysDao(Connection database) {
        this.connection = database;
    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kurssi WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Kysymys o = new Kysymys(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kurssi");

        ResultSet rs = stmt.executeQuery();
        List<Kysymys> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            kurssit.add(new Kysymys(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return kurssit;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
}
