package com.letscode.resistence.controllers;

import com.letscode.resistence.models.Item;
import com.letscode.resistence.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItemController {

    /*
     * Uso da notation @Autowired para injeção de de dependência do service.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> postItem (@RequestBody Item item) {
        Item itemResponse = itemService.salvarItem(item);
        return ResponseEntity.status(201).body(item);
    }

}
