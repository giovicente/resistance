package com.letscode.resistence.controllers;

import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.services.InventarioService;
import com.letscode.resistence.services.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    /*
     * Uso da notation @Autowired para injeção de de dependência do service.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    InventarioService inventarioService;

    @Autowired
    RebeldeService rebeldeService;

    @PutMapping("/negociar/{id1}/{id2}")
    public ResponseEntity atualizarInventarios(@PathVariable (name = "id1") long idRebelde1,
                                               @PathVariable (name = "id2") long idRebelde2,
                                               @RequestBody List<Inventario> inventarioList) {


        if (rebeldeService.verificaTraidor(idRebelde1, idRebelde2)) {
            inventarioService.dividirItensNegociacao(idRebelde1, idRebelde2, inventarioList);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Há traidor(es) na negociação");
        }

        return (ResponseEntity) ResponseEntity.noContent();
    }
}
