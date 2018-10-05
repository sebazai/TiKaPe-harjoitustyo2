/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.harjoitustyo.domain;

/**
 *
 * @author sebserge
 */
public class Vastaus {
    private Integer id;
    private String vastausteksti;
    private Boolean onkoOikein;

    public Vastaus(Integer id, String vastausteksti, Boolean onkoOikein) {
        this.id = id;
        this.vastausteksti = vastausteksti;
        this.onkoOikein = onkoOikein;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Boolean getOikein() {
        return this.onkoOikein;
    }
    
    public String getVastausOikeus() {
        if (this.onkoOikein) {
            return "Oikein";
        } else {
            return "Väärin";
        }
    }

    public String getVastaus() {
        return vastausteksti;
    }

    public void setVastaus(String kyssari) {
        this.vastausteksti = kyssari;
    }

}
