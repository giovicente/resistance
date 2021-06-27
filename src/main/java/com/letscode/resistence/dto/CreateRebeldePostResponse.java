package com.letscode.resistence.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.resistence.enums.GeneroEnum;
import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Localizacao;

import java.util.List;

public class CreateRebeldePostResponse {

    private long id;

    private String nome;

    private GeneroEnum genero;

    private Localizacao localizacao;

    @JsonProperty("quantidade_denuncias_traicao")
    private int quantidadeDenunciasTraicao;

    @JsonProperty("traidor")
    private boolean isTraidor;

    @JsonProperty("pontuacao_total")
    private int pontuacaoTotal;

    private List<Inventario> inventario;

    // Construtor
    public CreateRebeldePostResponse() { }

    // Getters & Setters
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public GeneroEnum getGenero() { return genero; }

    public void setGenero(GeneroEnum genero) { this.genero = genero; }

    public Localizacao getLocalizacao() { return localizacao; }

    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public int getQuantidadeDenunciasTraicao() { return quantidadeDenunciasTraicao; }

    public void setQuantidadeDenunciasTraicao(int quantidadeDenunciasTraicao) {
        this.quantidadeDenunciasTraicao = quantidadeDenunciasTraicao;
    }

    public boolean isTraidor() { return isTraidor; }

    public void setTraidor(boolean traidor) { isTraidor = traidor; }

    public int getPontuacaoTotal() { return pontuacaoTotal; }

    public void setPontuacaoTotal(int pontuacaoTotal) { this.pontuacaoTotal = pontuacaoTotal; }

    public List<Inventario> getInventario() { return inventario; }

   public void setInventario(List<Inventario> inventario) { this.inventario = inventario; }
}
