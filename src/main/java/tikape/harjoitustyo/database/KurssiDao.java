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
    public Kurssi save(Kurssi kurssi) throws SQLException {
        AiheDao aihedao = new AiheDao(this.connection);
        PreparedStatement stmt =
                this.connection.prepareStatement("INSERT INTO Kurssi (nimi) VALUES (?);");
        stmt.setString(1, kurssi.getKurssi());
        stmt.executeUpdate();
        stmt.close();
        stmt = this.connection.prepareStatement("SELECT * FROM Kurssi"
                + " WHERE nimi = ?;");
        stmt.setString(1, kurssi.getKurssi());

        ResultSet rs = stmt.executeQuery();
        rs.next(); // vain 1 tulos

        Kurssi k = new Kurssi(rs.getInt("id"), rs.getString("nimi"));
        //jos kurssia ei ole, niin ei ole myöskään aihetta kurssilel
        rs.close();
        aihedao.save(new Aihe(kurssi.getUudenAiheenNimi(), kurssi.getUudenAiheenKysymys(),k.getId()));
        stmt.close();
        
        return k;
    }
    public String findKurssiNameForKyssari(Integer id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT nimi FROM Kurssi, Aihe, Kysymys WHERE Kysymys.id = ? AND Kysymys.aihe_id = Aihe.id AND Kurssi.id = Aihe.kurssi_id;");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getString("nimi");
    }
    
    public Kurssi findKurssiOrCreateIt(String kurssinnimi, String aihe, String kysymys) throws SQLException {
        AiheDao aihedao = new AiheDao(this.connection);
        Kurssi k;
        PreparedStatement stmt
                    = this.connection.prepareStatement("SELECT * FROM Kurssi WHERE LOWER(Kurssi.nimi) = ?");
            stmt.setString(1, kurssinnimi.toLowerCase());
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            k = save(new Kurssi(kurssinnimi, aihe, kysymys));
        } else {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            //tarkistetaan onko aihetta ja jos, niin lisätään se kurssille
            Aihe a = aihedao.findAiheWithName(id, aihe, kysymys);
            List<Aihe> aiheet = new ArrayList<>();
            aiheet.add(a);
            k = new Kurssi(id, nimi, aiheet);
        }
        rs.close();
        return k;
    }
}