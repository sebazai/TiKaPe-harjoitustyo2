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
    private AiheDao aihedao;
    
    public KurssiDao(Connection database) {
        this.connection = database;
        this.aihedao = new AiheDao(this.connection);
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
        List<Aihe> aiheet = this.aihedao.findAllWithKurssiID(id);

        Kurssi k = new Kurssi(id, nimi, aiheet);

        rs.close();
        stmt.close();

        return k;
    }

    @Override
    public List<Kurssi> findAll() throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM Kurssi");

        ResultSet rs = stmt.executeQuery();
        List<Kurssi> kurssit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            List<Aihe> aiheet = this.aihedao.findAllWithKurssiID(id);
            kurssit.add(new Kurssi(id, nimi, aiheet));
        }

        rs.close();
        stmt.close();

        return kurssit;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    @Override
    public void save(Kurssi object) throws SQLException {

    }
    
    public String findKurssiNameForKyssari(Integer id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT nimi FROM Kurssi, Aihe, Kysymys WHERE Kysymys.id = ? AND Kysymys.aihe_id = Aihe.id AND Kurssi.id = Aihe.kurssi_id;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        return rs.getString("nimi");
    }
}