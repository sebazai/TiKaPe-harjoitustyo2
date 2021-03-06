/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harjoitustyo.domain;

import java.util.List;

/**
 *
 * @author sebserge
 */
public class Kysymys {
    
    private Integer id;
    private String kyssari;
    private List<Vastaus> vastaukset;
    private Integer aihe_id;

    public Kysymys(Integer id, String kyssari, List<Vastaus> vastaukset) {
        this.id = id;
        this.kyssari = kyssari;
        this.vastaukset = vastaukset;
    }
    
    public Kysymys(String kyssari, Integer aihe_id) {
        this.kyssari = kyssari;
        this.aihe_id = aihe_id;
    }
    
    public Integer getAiheId() {
        return this.aihe_id;
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
        return this.kyssari;
    }

    public void setKysymys(String kyssari) {
        this.kyssari = kyssari;
    }

}
