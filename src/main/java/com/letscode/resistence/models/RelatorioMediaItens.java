package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioMediaItens {

    @JsonProperty("media_armas")
    private double mediaArmas;

    @JsonProperty("media_municao")
    private double mediaMunicao;

    @JsonProperty("media_agua")
    private double mediaAgua;

    @JsonProperty("media_comida")
    private double mediaComida;

    public RelatorioMediaItens() { }

    public double getMediaArmas() { return mediaArmas; }

    public void setMediaArmas(double mediaArmas) { this.mediaArmas = mediaArmas; }

    public double getMediaMunicao() { return mediaMunicao; }

    public void setMediaMunicao(double mediaMunicao) { this.mediaMunicao = mediaMunicao; }

    public double getMediaAgua() { return mediaAgua; }

    public void setMediaAgua(double mediaAgua) { this.mediaAgua = mediaAgua; }

    public double getMediaComida() { return mediaComida; }

    public void setMediaComida(double mediaComida) { this.mediaComida = mediaComida; }
}
