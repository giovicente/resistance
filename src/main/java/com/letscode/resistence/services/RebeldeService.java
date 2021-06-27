package com.letscode.resistence.services;

import com.letscode.resistence.mappers.MapperRebelde;
import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Rebelde;
import com.letscode.resistence.repositories.RebeldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RebeldeService {

    /*
     * Uso da notation @Autowired para injeção de de dependência do repository.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    private RebeldeRepository rebeldeRepository;

    @Autowired
    private InventarioService inventarioService;

    private MapperRebelde mapperRebelde;

    private static final int QUANTIDADE_DENUNCIAS_TRAIDOR = 3;

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

    public Optional<Rebelde> buscarRebeldePorId(Long idRebelde) {
        return rebeldeRepository.findById(idRebelde);
    }

    public Iterable<Rebelde> consultarListaDeRebeldes() {
        Iterable<Rebelde> rebeldes = rebeldeRepository.findAll();
        return rebeldes;
    }

    public Rebelde receberDenunciaTraicao(long idRebelde) {
        Optional<Rebelde> rebeldeOptional = rebeldeRepository.findById(idRebelde);

        if (rebeldeOptional.isPresent()) {
            mapperRebelde = new MapperRebelde();
            Rebelde rebeldeReportado = mapperRebelde.criarRebeldeReportado(rebeldeOptional);

            rebeldeReportado.setId(idRebelde);

            int quantidadeDenunciasTraicao = rebeldeReportado.getQuantidadeDenunciasTraicao();
            quantidadeDenunciasTraicao++;
            rebeldeReportado.setQuantidadeDenunciasTraicao(quantidadeDenunciasTraicao);

            if (quantidadeDenunciasTraicao == QUANTIDADE_DENUNCIAS_TRAIDOR) {
                rebeldeReportado.setTraidor(true);
            }

            Rebelde rebeldeResponse = cadastrarRebelde(rebeldeReportado);

            return rebeldeResponse;
        }

        throw new RuntimeException("Rebelde não encontrado");
    }
}
