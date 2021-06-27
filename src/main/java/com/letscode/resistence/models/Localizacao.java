package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("id_rebelde")
    private long idRebelde;

    private double latitude;

    private double longitude;

    @JsonProperty("nome_base_galaxia")
    private String nomeBaseGalaxia;

    // Construtor
    public Localizacao() { }

    // Getters & Setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getIdRebelde() { return idRebelde; }

    public void setIdRebelde(long idRebelde) { this.idRebelde = idRebelde; }

    public double getLongitude() { return longitude; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getNomeBaseGalaxia() { return nomeBaseGalaxia; }

    public void setNomeBaseGalaxia(String nomeBaseGalaxia) { this.nomeBaseGalaxia = nomeBaseGalaxia; }

}
