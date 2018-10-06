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
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE Vastaus");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("DROP TABLE Kysymys");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("DROP TABLE Aihe");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("DROP TABLE Kurssi");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("CREATE TABLE Kurssi (id SERIAL PRIMARY KEY, nimi VARCHAR(100));");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("CREATE TABLE Aihe (id SERIAL PRIMARY KEY, aiheenNimi VARCHAR(100), kurssi_id INTEGER, FOREIGN KEY (kurssi_id) REFERENCES Kurssi (id));");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("CREATE TABLE Kysymys (id SERIAL PRIMARY KEY, kyssari VARCHAR(255), aihe_id INTEGER, FOREIGN KEY (aihe_id) REFERENCES Aihe (id));");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("CREATE TABLE Vastaus (id SERIAL PRIMARY KEY, vastausteksti VARCHAR(255), onkoOikein BOOLEAN, kysymys_id INTEGER, FOREIGN KEY (kysymys_id) REFERENCES Kysymys (id));");
        stmt.execute();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kurssi (nimi) VALUES ('Ohjelmoinnin perusteet')");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kurssi (nimi) VALUES ('Tietokantojen perusteet')");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kurssi (nimi) VALUES ('Ohjelmoinnin jatkokurssi')");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Aihe (aiheenNimi, kurssi_id) VALUES ('Olio-ohjelmointi', 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Aihe (aiheenNimi, kurssi_id) VALUES ('SQL', 2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Aihe (aiheenNimi, kurssi_id) VALUES ('Tietokannan suunnittelu', 2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kysymys (kyssari, aihe_id) VALUES ('Mitä abstrahointi tarkoittaa?', 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kysymys (kyssari, aihe_id) VALUES ('Mitä SQL-komento GROUP BY tekee?', 2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Kysymys (kyssari, aihe_id) VALUES ('Mitä normalisointi tarkoittaa?', 3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ( 'Mielen toimintaa universaaleja totuuksia muodostaessa.', false, 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ( 'Ilmiöiden monimutkaisuuden rajaamista eli pelkistämistä.', true, 1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Taideteosten rakentamista todellisuutta jäljentelemättömän taiteen keinoin.',false,1)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Mäkihypyn normaalimäen siivoamista hyppääjiä varten.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Korollisten kenkien ostamista siten, että kaikista tulee saman pituisia.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Luvun jakamista itsellään.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Teräksen käsittelyä siten, että sillä on yhtenäinen ja hienojakoinen rakenne.',false,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Tietokannan muokkaamista siten, että siinä toisteisen tiedon tallentaminen vähenee.',true,3)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Ryhmittelee SQL-kyselyn tulokset GROUP BY -osaa seuraavien sarakkeiden nimien perusteella.',true,2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Lataa SQL-kieleen ryhmittelytoiminnallisuuden, jota käyttäjä voi käyttää seuraavissa kyselyissä.',false,2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Ohjaa kyselyn tulokset vastaukset ryhmittelyfunktiolle, joka ryhmittelee tulokset GROUP BY -osaa seuraavien sarakkeiden perusteella.',true,2)");
        stmt.executeUpdate();
        stmt.close();
        stmt = conn.prepareStatement("INSERT INTO Vastaus (vastausteksti, onkoOikein, kysymys_id) VALUES ('Käynnistää BY-sovelluksessa salatilan, jonka avulla SQL-komennoista tulee noin 8% tehokkaampia.',false,2)");
        stmt.executeUpdate();
        stmt.close();
    }
}
