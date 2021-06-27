package com.letscode.resistence.services;

import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Item;
import com.letscode.resistence.repositories.InventarioRepository;
import com.letscode.resistence.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    /*
     * Uso da notation @Autowired para injeção de de dependência do repository.
     * Com isso, não precisar ficar instanciando objetos deste tipo ao longo dos métodos implementados.
     */
    @Autowired
    InventarioRepository inventarioRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<Inventario> cadastrarInventario(long idRebelde, List<Inventario> inventarioList) {

        for (int i = 0; i < inventarioList.size(); i++) {
            inventarioList.get(i).setIdRebelde(idRebelde);
            Optional<Item> itemOptional = itemRepository.findByNome(inventarioList.get(i).getNomeItem());

            if (itemOptional.isPresent()) {
                inventarioList.get(i).setIdItem(itemOptional.get().getId());
            }

            inventarioRepository.save(inventarioList.get(i));
        }

        return inventarioList;
    }

    public int calcularPontuacao(List<Inventario> inventarioList) {
        int pontuacao = 0;

        for (int i = 0; i < inventarioList.size(); i++) {
            Optional<Item> itemOptional = itemRepository.findById(inventarioList.get(i).getIdItem());

            if (itemOptional.isPresent()) {
                pontuacao += (itemOptional.get().getPontos() * inventarioList.get(i).getQuantidade());
            }
        }

        return pontuacao;
    }
}
