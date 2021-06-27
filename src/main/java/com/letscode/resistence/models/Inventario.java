package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("id_item")
    private long idItem;

    @JsonProperty("id_rebelde")
    private long idRebelde;

    @JsonProperty("nome_item")
    private String nomeItem;

    private int quantidade;

    // Construtor
    public Inventario() { }

    // Getters & Setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getIdItem() { return idItem; }

    public void setIdItem(long idItem) { this.idItem = idItem; }

    public long getIdRebelde() { return idRebelde; }

    public void setIdRebelde(long idRebelde) { this.idRebelde = idRebelde; }

    public String getNomeItem() { return nomeItem; }

    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
