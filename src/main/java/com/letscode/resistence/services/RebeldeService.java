package com.letscode.resistence.services;

import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Rebelde;
import com.letscode.resistence.repositories.LocalizacaoRepository;
import com.letscode.resistence.repositories.RebeldeRepository;
import javassist.NotFoundException;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebeldeService {

    /*
     * Uso da notation @Autowired para injeção de de dependência do repository.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    private RebeldeRepository rebeldeRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private InventarioService inventarioService;

    public Rebelde cadastrarRebelde(Rebelde rebelde) {
        Rebelde rebeldeResponse = rebeldeRepository.save(rebelde);
        return rebeldeResponse;
    }

    public int atualizarPontuacao(long idRebelde, Rebelde rebelde, List<Inventario> inventarioList) {
        if (rebeldeRepository.existsById(idRebelde)) {
            int pontuacao = inventarioService.calcularPontuacao(inventarioList);
            rebelde.setId(idRebelde);
            rebelde.setPontuacaoTotal(pontuacao);
            cadastrarRebelde(rebelde);

            return pontuacao;
        }

        return 0;
    }
}
