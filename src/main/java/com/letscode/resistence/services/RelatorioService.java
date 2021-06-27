package com.letscode.resistence.services;


import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Rebelde;
import com.letscode.resistence.models.RelatorioMediaItens;
import com.letscode.resistence.repositories.InventarioRepository;
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

    @Autowired
    InventarioRepository inventarioRepository;

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

    public RelatorioMediaItens calculaMediaItens() {
        int countRebeldes = 0;

        double countArma = 0, countMunicao = 0, countAgua = 0, countComida = 0;

        Iterable<Rebelde> rebeldes = rebeldeRepository.findAll();
        for (Rebelde rebelde: rebeldes) countRebeldes++;

        Iterable<Inventario> inventario = inventarioRepository.findAll();
        for (Inventario inventarioIterable: inventario) {
             switch ((int) inventarioIterable.getIdItem()) {
                case 1:
                    countArma += inventarioIterable.getQuantidade();
                    break;
                case 2:
                    countMunicao += inventarioIterable.getQuantidade();
                    break;
                case 3:
                    countAgua += inventarioIterable.getQuantidade();
                    break;
                case 4:
                    countComida += inventarioIterable.getQuantidade();
                    break;
            }
        }

        RelatorioMediaItens relatorioMediaItensResponse = new RelatorioMediaItens();
        relatorioMediaItensResponse.setMediaArmas(countArma / countRebeldes);
        relatorioMediaItensResponse.setMediaMunicao(countMunicao / countRebeldes);
        relatorioMediaItensResponse.setMediaAgua(countAgua / countRebeldes);
        relatorioMediaItensResponse.setMediaComida(countComida / countRebeldes);

        return relatorioMediaItensResponse;
    }
}
