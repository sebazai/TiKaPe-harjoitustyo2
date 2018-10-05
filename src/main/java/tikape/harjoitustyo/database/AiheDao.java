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
    private KysymysDao kysymysdao;

    public AiheDao(Connection database) {
        this.connection = database;
        this.kysymysdao = new KysymysDao(connection);
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
        List<Kysymys> kyssari = this.kysymysdao.findAllKysymyksetWithAiheID(id);

        Aihe a = new Aihe(id, aiheenNimi, kyssari);

        rs.close();
        stmt.close();

        return a;
    }

    @Override
    public List<Aihe> findAll() throws SQLException {
        KysymysDao kysymysdao = new KysymysDao(connection);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aihe");

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String aiheennimi = rs.getString("aiheenNimi");
            List<Kysymys> kyssari = kysymysdao.findAllKysymyksetWithAiheID(id);
            aiheet.add(new Aihe(id, aiheennimi,kyssari));
        }

        rs.close();
        stmt.close();

        return aiheet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    @Override
    public void save(Aihe object) throws SQLException {
        
    }
    

    List<Aihe> findAllWithKurssiID(Integer id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Aihe WHERE kurssi_id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        List<Aihe> aiheetKurssille = new ArrayList<>();
        while (rs.next()) {
            Integer aihe_id = rs.getInt("id");
            String aiheennimi = rs.getString("aiheenNimi");
            List<Kysymys> aiheenKysymykset = this.kysymysdao.findAllKysymyksetWithAiheID(aihe_id);
            aiheetKurssille.add(new Aihe(aihe_id, aiheennimi, aiheenKysymykset));
        }

        rs.close();
        stmt.close();

        return aiheetKurssille;
    }

    public String findAiheNameForKyssari(Integer id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT aiheenNimi FROM Aihe, Kysymys WHERE Kysymys.id = ? AND Kysymys.aihe_id = Aihe.id;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        return rs.getString("aiheenNimi");
    }
    
}
