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
import tikape.harjoitustyo.domain.Kurssi;

public class KurssiDao implements Dao<Kurssi, Integer> {

    private Connection connection;
    private AiheDao aihedao = new AiheDao(connection);
    
    public KurssiDao(Connection database) {
        this.connection = database;
    }
    

    @Override
    public Kurssi findOne(Integer key) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kurssi WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        List<Aihe> aiheet = aihedao.findAllWithKurssiID(id);

        Kurssi o = new Kurssi(id, nimi, aiheet);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Kurssi> findAll() throws SQLException {
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kurssi");

        ResultSet rs = stmt.executeQuery();
        List<Kurssi> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            List<Aihe> aiheet = aihedao.findAllWithKurssiID(id);
            kurssit.add(new Kurssi(id, nimi, aiheet));
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
    
    @Override
    public Kurssi saveOrUpdate(Kurssi object) throws SQLException {
        // jos asiakkaalla ei ole pääavainta, oletetaan, että asiakasta
        // ei ole vielä tallennettu tietokantaan ja tallennetaan asiakas
        if (object.getId() == null) {
            //return save(object);
        } else {
            // muulloin päivitetään asiakas
            //return update(object);
        }
        return object;
    }

}