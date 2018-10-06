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
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Vastaus WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("vastausteksti");
        Boolean onkoOikein = rs.getBoolean("onkoOikein");

        Vastaus v = new Vastaus(id, nimi, onkoOikein);

        rs.close();
        stmt.close();

        return v;
    }

    @Override
    public List<Vastaus> findAll() throws SQLException {

        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Vastaus");

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> vastaukset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("vastausteksti");
            Boolean onkoOikein = rs.getBoolean("onkoOikein");
            vastaukset.add(new Vastaus(id, nimi, onkoOikein));
        }

        rs.close();
        stmt.close();

        return vastaukset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM Vastaus WHERE Vastaus.id = ?");
        stmt.setInt(1, key);
        stmt.execute();
        stmt.close();
    }
    
    @Override
    public Vastaus save(Vastaus object) throws SQLException {
      PreparedStatement stmt
                    = this.connection.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES (?, ?, ?)");
            stmt.setString(1, object.getVastaus());
            stmt.setBoolean(2, object.getOikein());
            stmt.setInt(3, object.getId());
            
            stmt.executeUpdate();
            return new Vastaus(0, "haamuvastaus", true);
    }

    List<Vastaus> findAllVastausWithKysymysID(Integer id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Vastaus WHERE kysymys_id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        List<Vastaus> vastauksetKysymykselle = new ArrayList<>();
        while (rs.next()) {
            Integer vastaus_id = rs.getInt("id");
            String vastaus = rs.getString("vastausteksti");
            Boolean onkoOikein = rs.getBoolean("onkoOikein");
            vastauksetKysymykselle.add(new Vastaus(vastaus_id, vastaus, onkoOikein));
        }

        rs.close();
        stmt.close();

        return vastauksetKysymykselle;
    }
}
