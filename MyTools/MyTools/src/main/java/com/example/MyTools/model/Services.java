package com.example.MyTools.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Services extends AbstractEntity {

    private String photo;

    private String BesoinDuService;

    private String Libelle;

    @OneToMany(mappedBy = "services")
    @JsonIgnore
    private List<Solution> solution;

    @OneToMany
    @JsonIgnore
    private List<Client> client;

    public Services(String photo, String besoinDuService, String libelle) {
        this.photo = photo;
        BesoinDuService = besoinDuService;
        Libelle = libelle;
    }

    public String getBesoinDuService() {
        return BesoinDuService;
    }

    public void setBesoinDuService(String besoinDuService) {
        BesoinDuService = besoinDuService;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public List<Solution> getSolution() {
        return solution;
    }

    public void setSolution(List<Solution> solution) {
        this.solution = solution;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
