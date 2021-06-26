package com.letscode.resistence.models;

import com.letscode.resistence.enums.GeneroEnum;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rebelde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private GeneroEnum genero;

    @OneToOne
    private Localizacao localizacao;

    @OneToMany
    private List<Item> inventario;

    private int quantidadeDenunciasTraicao;

    private boolean isTraidor;

    // Construtor
    public Rebelde() { }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public GeneroEnum getGenero() { return genero; }

    public void setGenero(GeneroEnum genero) { this.genero = genero; }

    public Localizacao getLocalizacao() { return localizacao; }

    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public List<Item> getInventario() { return inventario; }

    public void setInventario(List<Item> inventario) { this.inventario = inventario; }

}
