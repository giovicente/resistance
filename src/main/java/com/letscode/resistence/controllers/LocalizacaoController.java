package com.letscode.resistence.controllers;

import com.letscode.resistence.models.Localizacao;
import com.letscode.resistence.services.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    /*
     * Uso da notation @Autowired para injeção de de dependência do service.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    LocalizacaoService localizacaoService;

    @PutMapping("/{id}")
    public ResponseEntity<Localizacao> atualizarLocalizacao(@PathVariable(name = "id") long id, @RequestBody Localizacao localizacao) {
        try {
            Localizacao localizacaoResponse = localizacaoService.atualizarLocalizacao(id, localizacao);
            return ResponseEntity.status(200).body(localizacaoResponse);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
