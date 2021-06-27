package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("quantidade_denuncias_traicao")
    private int quantidadeDenunciasTraicao;

    @JsonProperty("traidor")
    private boolean isTraidor;

    @JsonProperty("pontuacao_total")
    private int pontuacaoTotal;

    // Construtor
    public Rebelde() { }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public GeneroEnum getGenero() { return genero; }

    public void setGenero(GeneroEnum genero) { this.genero = genero; }

    public int getQuantidadeDenunciasTraicao() { return quantidadeDenunciasTraicao; }

    public void setQuantidadeDenunciasTraicao(int quantidadeDenunciasTraicao) {
        this.quantidadeDenunciasTraicao = quantidadeDenunciasTraicao;
    }

    public boolean isTraidor() { return isTraidor; }

    public void setTraidor(boolean traidor) { isTraidor = traidor; }

    public int getPontuacaoTotal() { return pontuacaoTotal; }

    public void setPontuacaoTotal(int pontuacaoTotal) { this.pontuacaoTotal = pontuacaoTotal; }

}
