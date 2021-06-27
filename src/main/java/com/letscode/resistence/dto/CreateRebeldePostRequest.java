package com.letscode.resistence.dto;

import com.letscode.resistence.enums.GeneroEnum;
import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Localizacao;

import java.util.List;

public class CreateRebeldePostRequest {

    private String nome;

    private GeneroEnum genero;

    private Localizacao localizacao;

    private List<Inventario> inventario;

    // Construtor
    public CreateRebeldePostRequest() { }

    // Getters & Setters
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public GeneroEnum getGenero() { return genero; }

    public void setGenero(GeneroEnum genero) { this.genero = genero; }

    public Localizacao getLocalizacao() { return localizacao; }

    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public List<Inventario> getInventario() { return inventario; }

    public void setInventario(List<Inventario> inventario) { this.inventario = inventario; }
}
