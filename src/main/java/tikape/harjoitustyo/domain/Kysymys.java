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
public class Kysymys {
    
    private Integer id;
    private String kyssari;
    private ArrayList<Vastaus> vastaukset;

    public Kysymys(Integer id, String kyssari) {
        this.id = id;
        this.kyssari = kyssari;
        this.vastaukset = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }
    
    public void setVastaus(Vastaus vastaus) {
        this.vastaukset.add(vastaus);
    }
    
    public List<Vastaus> getVastaukset()  {
        return this.vastaukset;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKysymys() {
        return kyssari;
    }

    public void setKysymys(String kyssari) {
        this.kyssari = kyssari;
    }

}
