package com.letscode.resistence.controllers;

import com.letscode.resistence.dto.CreateRebeldePostRequest;
import com.letscode.resistence.dto.CreateRebeldePostResponse;
import com.letscode.resistence.mappers.MapperRebelde;
import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Localizacao;
import com.letscode.resistence.models.Rebelde;
import com.letscode.resistence.services.InventarioService;
import com.letscode.resistence.services.LocalizacaoService;
import com.letscode.resistence.services.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    /*
     * Uso da notation @Autowired para injeção de de dependência do service.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    RebeldeService rebeldeService;

    @Autowired
    LocalizacaoService localizacaoService;

    @Autowired
    InventarioService inventarioService;

    MapperRebelde mapperRebelde;

    @PostMapping
    public ResponseEntity<CreateRebeldePostResponse> postRebelde(@RequestBody CreateRebeldePostRequest createRebeldePostRequest) {
        mapperRebelde = new MapperRebelde();
        Rebelde rebelde = mapperRebelde.converterRebeldePostRequest(createRebeldePostRequest);
        Rebelde rebeldeResponse = rebeldeService.cadastrarRebelde(rebelde);

        Localizacao localizacaoResponse = localizacaoService
                .cadastrarLocalizacao(createRebeldePostRequest.getLocalizacao(), rebeldeResponse.getId());

        List<Inventario> inventarioList = inventarioService.cadastrarInventario(rebeldeResponse.getId(),
                createRebeldePostRequest.getInventario());

        CreateRebeldePostResponse response = mapperRebelde
                .converterParaCreateRebeldePostResponse(rebeldeResponse, localizacaoResponse, inventarioList);

        response.setPontuacaoTotal
                (rebeldeService.atualizarPontuacao(response.getId(), rebeldeResponse, response.getInventario()));

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public Iterable<Rebelde> getRebeldes() {
        Iterable<Rebelde> rebeldes = rebeldeService.consultarListaDeRebeldes();
        return rebeldes;
    }

    @PutMapping("/reportar/{id}")
    public ResponseEntity<Rebelde> denunciarTraicao(@PathVariable(name = "id") long id) {
        try {
            Rebelde rebeldeResponse = rebeldeService.receberDenunciaTraicao(id);
            return ResponseEntity.status(200).body(rebeldeResponse);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
