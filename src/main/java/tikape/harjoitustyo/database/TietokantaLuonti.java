/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harjoitustyo.database;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Sebu
 */
public class TietokantaLuonti {
    public void doYourThing(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Vastaus");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("DELETE FROM Kysymys");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("DELETE FROM Aihe");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("DELETE FROM Kurssi");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kurssi (id, nimi) VALUES (1, 'Ohjelmoinnin perusteet')");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kurssi (id, nimi) VALUES (2, 'Tietokantojen perusteet')");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kurssi (id, nimi) VALUES (3, 'Ohjelmoinnin jatkokurssi')");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Aihe (id, aiheenNimi, kurssi_id) VALUES (1,'Olio-ohjelmointi', 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Aihe (id, aiheenNimi, kurssi_id) VALUES (2,'SQL', 2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Aihe (id, aiheenNimi, kurssi_id) VALUES (3,'Tietokannan suunnittelu', 2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kysymys (id, kyssari, aihe_id) VALUES (1, 'Mitä abstrahointi tarkoittaa?', 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kysymys (id, kyssari, aihe_id) VALUES (2, 'Mitä SQL-komento GROUP BY tekee?', 2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kysymys (id, kyssari, aihe_id) VALUES (3, 'Mitä normalisointi tarkoittaa?', 3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (1, 'Mielen toimintaa universaaleja totuuksia muodostaessa.', false, 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (2, 'Ilmiöiden monimutkaisuuden rajaamista eli pelkistämistä.', true, 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (3,'Taideteosten rakentamista todellisuutta jäljentelemättömän taiteen keinoin.',false,1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (4,'Mäkihypyn normaalimäen siivoamista hyppääjiä varten.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (5,'Korollisten kenkien ostamista siten, että kaikista tulee saman pituisia.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (6,'Luvun jakamista itsellään.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (7,'Teräksen käsittelyä siten, että sillä on yhtenäinen ja hienojakoinen rakenne.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (8,'Tietokannan muokkaamista siten, että siinä toisteisen tiedon tallentaminen vähenee.',true,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (9,'Ryhmittelee SQL-kyselyn tulokset GROUP BY -osaa seuraavien sarakkeiden nimien perusteella.',true,2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (10,'Lataa SQL-kieleen ryhmittelytoiminnallisuuden, jota käyttäjä voi käyttää seuraavissa kyselyissä.',false,2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (11,'Ohjaa kyselyn tulokset vastaukset ryhmittelyfunktiolle, joka ryhmittelee tulokset GROUP BY -osaa seuraavien sarakkeiden perusteella.',true,2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (id, vastausteksti, onkoOikein, kysymys_id) VALUES (12,'Käynnistää BY-sovelluksessa salatilan, jonka avulla SQL-komennoista tulee noin 8% tehokkaampia.',false,2)");
        stmt.executeUpdate();
        stmt.close();
    }
}
