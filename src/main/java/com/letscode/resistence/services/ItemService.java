package com.letscode.resistence.services;

import com.letscode.resistence.models.Item;
import com.letscode.resistence.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    /*
    * Uso da notation @Autowired para injeção de de dependência do repository.
    * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
    */
    @Autowired
    private ItemRepository itemRepository;

    public Item salvarItem(Item item) {
        Item itemResponse = itemRepository.save(item);
        return itemResponse;
    }

}
