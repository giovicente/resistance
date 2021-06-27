package com.letscode.resistence.services;


import com.letscode.resistence.models.Rebelde;
import com.letscode.resistence.repositories.RebeldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    /*
     * Uso da notation @Autowired para injeção de de dependência do service.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    RebeldeRepository rebeldeRepository;

    public double obterPercentualTraidores() {
        int countTotal = 0, countTraidores = 0;

        Iterable<Rebelde> rebeldes = rebeldeRepository.findAll();

        for (Rebelde rebelde: rebeldes) {
            if (rebelde.isTraidor()){
                countTraidores++;
            }
            countTotal++;
        }

        if (countTotal == 0) return 0;

        return (countTraidores * 100) / countTotal;
    }

    public double obterPercentualRebeldes() {
        int countTotal = 0, countRebeldes = 0;

        Iterable<Rebelde> rebeldes = rebeldeRepository.findAll();

        for (Rebelde rebelde: rebeldes) {
            if (!rebelde.isTraidor()){
                countRebeldes++;
            }
            countTotal++;
        }

        if (countTotal == 0) return 0;

        return (countRebeldes * 100) / countTotal;
    }

    public int calculaPontosPerdidos() {
        int countPontosPerdidos = 0;

        Iterable<Rebelde> rebeldes = rebeldeRepository.findAll();

        for (Rebelde rebelde: rebeldes) {
            if (rebelde.isTraidor()){
                countPontosPerdidos += rebelde.getPontuacaoTotal();
            }
        }

        return countPontosPerdidos;
    }
}
