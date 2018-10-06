# Tietokantojen perusteet Harjoitustyö 2
Jos haluat paikallisesti ajaa tätä, niin TietokantaLuonti.java pitää muuttaa kaikki nämä:
1. id SERIAL PRIMARY KEY -> id INTEGER PRIMARY KEY
2. sanat "false" -> 0
3. sanat "true" -> 1

AiheDao.java:ssa muutettava rivi 136 SQL-kysely seuraavaksi

SELECT * FROM Aihe WHERE aiheenNimi = ?

Samoin KurssiDao, rivi 110 SQL-kysely

SELECT * FROM Kurssi WHERE Kurssi.nimi = ?

Tämän pitäisi toimia paikallisesti, mutta silloin Kurssinimi ja Aihe on case-sensitive, mikäli lisäät uusia kysymyksiä Kurssille ja Aiheelle.

Heroku linkki: https://pure-inlet-36906.herokuapp.com/
