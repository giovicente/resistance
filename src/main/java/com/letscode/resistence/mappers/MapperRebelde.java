package com.letscode.resistence.mappers;

import com.letscode.resistence.dto.CreateRebeldePostRequest;
import com.letscode.resistence.dto.CreateRebeldePostResponse;
import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Localizacao;
import com.letscode.resistence.models.Rebelde;

import java.util.List;
import java.util.Optional;

public class MapperRebelde {

    public Rebelde converterRebeldePostRequest (CreateRebeldePostRequest createRebeldePostRequest) {
        Rebelde rebelde = new Rebelde();

        rebelde.setNome(createRebeldePostRequest.getNome());
        rebelde.setGenero(createRebeldePostRequest.getGenero());
        // Quando um rebelde eh cadastrado, ele comeca com 0 denuncias de traicao
        rebelde.setQuantidadeDenunciasTraicao(0);
        // Quando um rebelde eh cadastrado, nao devemos considera-lo um traidor
        rebelde.setTraidor(false);
        // A pontuacao total sera calculada na camada de service, apos cadastro do inventario
        rebelde.setPontuacaoTotal(0);

        return rebelde;
    }

    public CreateRebeldePostResponse
        converterParaCreateRebeldePostResponse (Rebelde rebelde, Localizacao localizacao, List<Inventario> inventarioList) {

        CreateRebeldePostResponse createRebeldePostResponse = new CreateRebeldePostResponse();

        createRebeldePostResponse.setId(rebelde.getId());
        createRebeldePostResponse.setNome(rebelde.getNome());
        createRebeldePostResponse.setGenero(rebelde.getGenero());
        createRebeldePostResponse.setLocalizacao(localizacao);
        createRebeldePostResponse.setQuantidadeDenunciasTraicao(rebelde.getQuantidadeDenunciasTraicao());
        createRebeldePostResponse.setPontuacaoTotal(rebelde.getPontuacaoTotal());
        createRebeldePostResponse.setTraidor(rebelde.isTraidor());
        createRebeldePostResponse.setInventario(inventarioList);

        return createRebeldePostResponse;
    }

    public Rebelde criarRebeldeReportado(Optional<Rebelde> rebeldeOptional) {
        Rebelde rebeldeReportado = new Rebelde();

        rebeldeReportado.setId(rebeldeOptional.get().getId());
        rebeldeReportado.setNome(rebeldeOptional.get().getNome());
        rebeldeReportado.setGenero(rebeldeOptional.get().getGenero());
        rebeldeReportado.setTraidor(rebeldeOptional.get().isTraidor());
        rebeldeReportado.setQuantidadeDenunciasTraicao(rebeldeOptional.get().getQuantidadeDenunciasTraicao());
        rebeldeReportado.setPontuacaoTotal(rebeldeOptional.get().getPontuacaoTotal());

        return rebeldeReportado;
    }
}
