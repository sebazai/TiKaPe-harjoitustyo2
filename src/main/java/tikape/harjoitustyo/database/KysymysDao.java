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
       private VastausDao vastausdao;

    public KysymysDao(Connection database) {
        this.connection = database;
        this.vastausdao = new VastausDao(this.connection);
    }

    @Override
    public Kysymys findOne(Integer key) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Kysymys WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String kyssari = rs.getString("kyssari");
        List<Vastaus> kysymyksenVastaukset = this.vastausdao.findAllVastausWithKysymysID(id);
        Kysymys k = new Kysymys(id, kyssari, kysymyksenVastaukset);

        rs.close();
        stmt.close();

        return k;
    }

    @Override
    public List<Kysymys> findAll() throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Kysymys");

        ResultSet rs = stmt.executeQuery();
        List<Kysymys> kysymykset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String kyssari = rs.getString("kyssari");
             List<Vastaus> kysymyksenVastaukset = this.vastausdao.findAllVastausWithKysymysID(id);
            kysymykset.add(new Kysymys(id, kyssari, kysymyksenVastaukset));
        }

        rs.close();
        stmt.close();

        return kysymykset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    @Override
    public void save(Kysymys object) throws SQLException {
   
    }

    List<Kysymys> findAllKysymyksetWithAiheID(Integer id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Kysymys WHERE aihe_id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        List<Kysymys> kysymyksetAiheelle = new ArrayList<>();
        while (rs.next()) {
            Integer kysymys_id = rs.getInt("id");
            String kyssari = rs.getString("kyssari");
            List<Vastaus> kysymyksenVastaukset = this.vastausdao.findAllVastausWithKysymysID(kysymys_id);
            kysymyksetAiheelle.add(new Kysymys(kysymys_id, kyssari, kysymyksenVastaukset));
        }

        rs.close();
        stmt.close();

        return kysymyksetAiheelle;
    }
}
