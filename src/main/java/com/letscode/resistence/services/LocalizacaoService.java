package com.letscode.resistence.services;

import com.letscode.resistence.models.Localizacao;
import com.letscode.resistence.models.Rebelde;
import com.letscode.resistence.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalizacaoService {

    /*
     * Uso da notation @Autowired para injeção de de dependência do repository.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    LocalizacaoRepository localizacaoRepository;

    @Autowired
    RebeldeService rebeldeService;

    public Localizacao cadastrarLocalizacao(Localizacao localizacao, long idRebelde) {
        Optional<Rebelde> rebeldeOptional = rebeldeService.buscarRebeldePorId(idRebelde);

        if (rebeldeOptional.isPresent()) {
            localizacao.setIdRebelde(rebeldeOptional.get().getId());
        }

        Localizacao localizacaoResponse = localizacaoRepository.save(localizacao);
        return localizacaoResponse;
    }
}
