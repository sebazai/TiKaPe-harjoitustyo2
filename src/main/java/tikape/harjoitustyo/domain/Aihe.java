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
public class Aihe {
    
    private Integer id;
    private String aiheenNimi;
    private List<Kysymys> kysymykset;

    public Aihe(Integer id, String aiheenNimi, List<Kysymys> kyssarit) {
        this.id = id;
        this.aiheenNimi = aiheenNimi;
        this.kysymykset = kyssarit;
    }

    public Integer getId() {
        return id;
    }
    
    public List<Kysymys> getKysymykset() {
        return this.kysymykset;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAihe() {
        return aiheenNimi;
    }

    public void setAihe(String aiheenNimi) {
        this.aiheenNimi = aiheenNimi;
    }

}
