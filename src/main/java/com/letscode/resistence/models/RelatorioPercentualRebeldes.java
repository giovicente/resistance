package com.letscode.resistence.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatorioPercentualRebeldes {

    @JsonProperty("percentual_rebeldes")
    private double percentual;

    public RelatorioPercentualRebeldes() { }

    public double getPercentual() { return percentual; }

    public void setPercentual(double percentual) { this.percentual = percentual; }
}
