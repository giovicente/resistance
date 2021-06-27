package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioPercentualTraidor {

    @JsonProperty("percentual_traidores")
    private double percentual;

    public RelatorioPercentualTraidor() { }

    public double getPercentual() { return percentual; }

    public void setPercentual(double percentual) { this.percentual = percentual; }
}
