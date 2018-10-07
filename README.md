# Tietokantojen perusteet Harjoitustyö 2

Heroku linkki: https://pure-inlet-36906.herokuapp.com/

Sovellus tarjoaa seuraavat toiminnallisuudet:

	1. Mahdollisuus kysymysten lisäämiseen palveluun. 
	2. Mahdollisuus vastausvaihtoehtojen lisäämiseen kysymykseen. 
	3. Kysymyksen poistaminen
	4. Vastausvaihtoehdon poistaminen
	5. Kysymysten listaus
	6. Kysymyksen katsominen ja tämän vastaukset

Jos haluat paikallisesti ajaa tätä, niin TietokantaLuonti.java pitää muuttaa kaikki nämä:
1. id SERIAL PRIMARY KEY -> id INTEGER PRIMARY KEY
2. sanat "false" -> 0
3. sanat "true" -> 1

AiheDao.java:ssa muutettava rivi 136 SQL-kysely seuraavaksi

	SELECT * FROM Aihe WHERE aiheenNimi = ?

Samoin KurssiDao, rivi 110 SQL-kysely

	SELECT * FROM Kurssi WHERE Kurssi.nimi = ?

Tämän pitäisi toimia paikallisesti, mutta silloin Kurssinimi ja Aihe on case-sensitive, mikäli lisäät uusia kysymyksiä Kurssille ja Aiheelle.

# Tietokannat
CREATE TABLE Kurssi (id SERIAL PRIMARY KEY, nimi VARCHAR(100));


CREATE TABLE Aihe (id SERIAL PRIMARY KEY, aiheenNimi VARCHAR(100), kurssi_id INTEGER NOT NULL, FOREIGN KEY (kurssi_id) REFERENCES Kurssi (id));


CREATE TABLE Kysymys (id SERIAL PRIMARY KEY, kyssari VARCHAR(255), aihe_id INTEGER NOT NULL, FOREIGN KEY (aihe_id) REFERENCES Aihe (id));


CREATE TABLE Vastaus (id SERIAL PRIMARY KEY, vastausteksti VARCHAR(255), onkoOikein BOOLEAN, kysymys_id INTEGER NOT NULL, FOREIGN KEY (kysymys_id) REFERENCES Kysymys (id));

