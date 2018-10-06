package tikape.harjoitustyo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.harjoitustyo.database.AiheDao;
import tikape.harjoitustyo.domain.Kurssi;
import tikape.harjoitustyo.database.KurssiDao;
import tikape.harjoitustyo.database.KysymysDao;
import tikape.harjoitustyo.database.VastausDao;
import tikape.harjoitustyo.domain.Kysymys;
import tikape.harjoitustyo.domain.Vastaus;

public class Main {

    public static void main(String[] args) throws Exception {
        // asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }
        
        //index.html haku
        Spark.get("/", (req, res) -> {
            Connection conn = getConnection();
            KurssiDao kurssidao = new KurssiDao(conn);
            List<Kurssi> kurssit = kurssidao.findAll();

            HashMap map = new HashMap<>();

            map.put("lista", kurssit);
            conn.close();

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        //haetaan kaikki kysymyksen vastaukset ja aihepiiri + kurssinimi
        Spark.get("/kysymys/:id", (req, res) -> {
            Connection conn = getConnection();
            KysymysDao kysymysdao = new KysymysDao(conn);
            AiheDao aihedao = new AiheDao(conn);
            KurssiDao kurssidao = new KurssiDao(conn);
            Kysymys kyssari = kysymysdao.findOne(Integer.parseInt(req.params(":id")));
            String aihepiiri = aihedao.findAiheNameForKyssari(kyssari.getId());
            String kurssinimi = kurssidao.findKurssiNameForKyssari(kyssari.getId());
            
            HashMap map = new HashMap<>();
            
            map.put("kysymys", kyssari);
            map.put("kurssi", kurssinimi);
            map.put("aihepiiri", aihepiiri);
            conn.close();

            return new ModelAndView(map, "kysymys");
        }, new ThymeleafTemplateEngine());
        
        //uusi kysymys pääsivulta
        Spark.post("/uusikysymys", (req, res) -> {
            Connection conn = getConnection();
            KurssiDao kurssidao = new KurssiDao(conn);
            Kurssi kurssi = kurssidao.findKurssiOrCreateIt(req.queryParams("kurssi"), 
                    req.queryParams("aihe"), req.queryParams("kysymysteksti"));
            
            conn.close();

            res.redirect("/");
            return "";
        });
        
        //uusi vastaus kysymykseen
        Spark.post("/kysymys/:id", (req, res) -> {
            Connection conn = getConnection();
            VastausDao vastausdao = new VastausDao(conn);
            Boolean onkoOikein = false;
            if (req.queryParams("totta") != null) {
                onkoOikein = true;
            }
            int kysymysId = Integer.parseInt(req.params(":id"));
            Vastaus v = vastausdao.save(new Vastaus(kysymysId, req.queryParams("vastausteksti"), onkoOikein));
            res.redirect("/kysymys/" + kysymysId);
            return "";
        });
        
        //poistetaan vastaus postilla /kysymys/1/vastaus/4 esim.
        Spark.post("/kysymys/:id/vastaus/:vastaus_id", (req, res) -> {
            Connection conn = getConnection();
            int poistettavaVastaus = Integer.parseInt(req.params(":vastaus_id"));
            VastausDao vastausdao = new VastausDao(conn);
            vastausdao.delete(poistettavaVastaus);
            conn.close();
            res.redirect("/kysymys/" + req.params(":id"));
            return "";
        });
    }
    
    //haetaan heroku tietokanta tai paikallinen
    public static Connection getConnection() throws Exception {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }

        return DriverManager.getConnection("jdbc:sqlite:harjoitustyo.db");
    }

}
