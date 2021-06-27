package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioContagemPontosPeridos {

    @JsonProperty("pontos_perdidos_traicao")
    private int pontosPerdidos;

    public RelatorioContagemPontosPeridos() { }

    public int getPontosPerdidos() { return pontosPerdidos; }

    public void setPontosPerdidos(int pontosPerdidos) { this.pontosPerdidos = pontosPerdidos; }
}
