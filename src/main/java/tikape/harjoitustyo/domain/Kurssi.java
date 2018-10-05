/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harjoitustyo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebserge
 */
public class Kurssi {


    private Integer id;
    private String nimi;
    private List<Aihe> aiheet;

    public Kurssi(Integer id, String nimi, List<Aihe> aiheet) {
        this.id = id;
        this.nimi = nimi;
        this.aiheet = aiheet;
    }
    
    public List<Aihe> getAiheet() {
        return this.aiheet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKurssi() {
        return nimi;
    }

    public void setKurssi(String nimi) {
        this.nimi = nimi;
    }

}
