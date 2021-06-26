package com.letscode.resistence.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private int quantidade;

    private int pontos;

    // Construtor
    public Item() { }

    // Getters & Setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getPontos() { return pontos; }

    public void setPontos(int pontos) { this.pontos = pontos; }

}
