package com.letscode.resistence.controllers;

import com.letscode.resistence.models.RelatorioContagemPontosPeridos;
import com.letscode.resistence.models.RelatorioPercentualRebeldes;
import com.letscode.resistence.models.RelatorioPercentualTraidor;
import com.letscode.resistence.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    /*
     * Uso da notation @Autowired para injeção de de dependência do service.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/traidores")
    public RelatorioPercentualTraidor getTraidores() {
        RelatorioPercentualTraidor relatorioPercentualTraidor = new RelatorioPercentualTraidor();
        relatorioPercentualTraidor.setPercentual(relatorioService.obterPercentualTraidores());

        return relatorioPercentualTraidor;
    }

    @GetMapping("/rebeldes")
    public RelatorioPercentualRebeldes getRebeldes() {
        RelatorioPercentualRebeldes relatorioPercentualRebeldes = new RelatorioPercentualRebeldes();
        relatorioPercentualRebeldes.setPercentual(relatorioService.obterPercentualRebeldes());

        return relatorioPercentualRebeldes;
    }

    @GetMapping("/pontos_perdidos")
    public RelatorioContagemPontosPeridos getPontosPerdidos() {
        RelatorioContagemPontosPeridos relatorioContagemPontosPeridos = new RelatorioContagemPontosPeridos();
        relatorioContagemPontosPeridos.setPontosPerdidos(relatorioService.calculaPontosPerdidos());

        return relatorioContagemPontosPeridos;
    }

}
