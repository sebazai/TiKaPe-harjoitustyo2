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
import tikape.harjoitustyo.domain.Aihe;

/**
 *
 * @author sebserge
 */
public class AiheDao implements Dao<Aihe, Integer>{
        private Connection connection;

    public AiheDao(Connection database) {
        this.connection = database;
    }

    @Override
    public Aihe findOne(Integer key) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kurssi WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Aihe o = new Aihe(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Aihe> findAll() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kurssi");

        ResultSet rs = stmt.executeQuery();
        List<Aihe> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            kurssit.add(new Aihe(id, nimi));
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
