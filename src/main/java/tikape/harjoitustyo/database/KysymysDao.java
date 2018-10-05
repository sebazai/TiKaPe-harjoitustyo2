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
import tikape.harjoitustyo.domain.Vastaus;

/**
 *
 * @author sebserge
 */
public class KysymysDao implements Dao<Kysymys, Integer>  {
       private Connection connection;
       private VastausDao vastausdao = new VastausDao(connection);

    public KysymysDao(Connection database) {
        this.connection = database;
    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kysymys WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String kyssari = rs.getString("kyssari");
        List<Vastaus> kysymyksenVastaukset = vastausdao.findAllVastausWithKysymysID(id);
        Kysymys o = new Kysymys(id, kyssari, kysymyksenVastaukset);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kysymys");

        ResultSet rs = stmt.executeQuery();
        List<Kysymys> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String kyssari = rs.getString("kyssari");
             List<Vastaus> kysymyksenVastaukset = vastausdao.findAllVastausWithKysymysID(id);
            kurssit.add(new Kysymys(id, kyssari, kysymyksenVastaukset));
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
    public Kysymys saveOrUpdate(Kysymys object) throws SQLException {
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

    List<Kysymys> findAllKysymyksetWithAiheID(Integer id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kysymys WHERE aihe_id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        List<Kysymys> kysymyksetAiheelle = new ArrayList<>();
        while (rs.next()) {
            Integer kysymys_id = rs.getInt("id");
            String kyssari = rs.getString("kyssari ");
            List<Vastaus> kysymyksenVastaukset = vastausdao.findAllVastausWithKysymysID(kysymys_id);
            kysymyksetAiheelle.add(new Kysymys(kysymys_id, kyssari, kysymyksenVastaukset));
        }

        rs.close();
        stmt.close();
        connection.close();

        return kysymyksetAiheelle;
    }
}
