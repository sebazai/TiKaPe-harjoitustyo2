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
import tikape.harjoitustyo.domain.Kysymys;

/**
 *
 * @author sebserge
 */
public class AiheDao implements Dao<Aihe, Integer>{
    private Connection connection;
    private KysymysDao kysymysdao = new KysymysDao(connection);

    public AiheDao(Connection database) {
        this.connection = database;
    }

    @Override
    public Aihe findOne(Integer key) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String aiheenNimi = rs.getString("aiheenNimi");
        List<Kysymys> kyssari = kysymysdao.findAllKysymyksetWithAiheID(id);

        Aihe o = new Aihe(id, aiheenNimi, kyssari);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Aihe> findAll() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe");

        ResultSet rs = stmt.executeQuery();
        List<Aihe> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String aiheennimi = rs.getString("aiheenNimi");
            List<Kysymys> kyssari = kysymysdao.findAllKysymyksetWithAiheID(id);
            kurssit.add(new Aihe(id, aiheennimi,kyssari));
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
    public Aihe saveOrUpdate(Aihe object) throws SQLException {
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
    
     /*private Aihe save(Aihe aihe) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Todo"
                + " (tehtava, tehty)"
                + " VALUES (?, ?)");
        stmt.setString(1, aihe.getAihe());
        stmt.setBoolean(2, aihe.getTehty());

        stmt.executeUpdate();
        stmt.close();

        stmt = connection.prepareStatement("SELECT * FROM Todo"
                + " WHERE nimi = ? AND puhelinnumero = ?");
        stmt.setString(1, aihe.getTehtava());
        stmt.setBoolean(2, aihe.getTehty());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Aihe t = new Aihe(rs.getInt("id"), rs.getString("tehtava"));

        stmt.close();
        rs.close();

        connection.close();

        return t;
    }

    private Todo update(Todo tehtavatodo) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Todo SET"
                + " tehtava = ?, puhelinnumero = ?, katuosoite = ?, postinumero = ?, postitoimipaikka = ? WHERE id = ?");
        stmt.setString(1, tehtavatodo.getTehtava());
        stmt.setBoolean(2, tehtavatodo.getTehty());
        stmt.setInt(3, tehtavatodo.getId());


        stmt.executeUpdate();

        stmt.close();
        conn.close();

        return tehtavatodo;
    }*/

    List<Aihe> findAllWithKurssiID(Integer id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe WHERE kurssi_id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheetKurssille = new ArrayList<>();
        while (rs.next()) {
            Integer aihe_id = rs.getInt("id");
            String aiheennimi = rs.getString("aiheenNimi");
            List<Kysymys> aiheenKysymykset = kysymysdao.findAllKysymyksetWithAiheID(aihe_id);
            aiheetKurssille.add(new Aihe(aihe_id, aiheennimi, aiheenKysymykset));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aiheetKurssille;
    }
    
}
