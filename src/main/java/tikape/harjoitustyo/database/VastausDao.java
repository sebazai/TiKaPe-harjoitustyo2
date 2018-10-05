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
import tikape.harjoitustyo.domain.Vastaus;

/**
 *
 * @author sebserge
 */
public class VastausDao implements Dao<Vastaus, Integer>  {
       private Connection connection;

    public VastausDao(Connection database) {
        this.connection = database;
    }

    @Override
    public Vastaus findOne(Integer key) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        Boolean onkoOikein = rs.getBoolean("onkoOikein");

        Vastaus o = new Vastaus(id, nimi, onkoOikein);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Vastaus> findAll() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vastaus");

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Boolean onkoOikein = rs.getBoolean("onkoOikein");

            kurssit.add(new Vastaus(id, nimi, onkoOikein));
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
