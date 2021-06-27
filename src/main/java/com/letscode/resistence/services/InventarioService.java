package com.letscode.resistence.services;

import com.letscode.resistence.models.Inventario;
import com.letscode.resistence.models.Item;
import com.letscode.resistence.repositories.InventarioRepository;
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
    ItemService itemService;

    @Autowired
    RebeldeService rebeldeService;

    private List<Inventario> inventarioNegociante1, inventarioNegociante2;

    public List<Inventario> cadastrarInventario(long idRebelde, List<Inventario> inventarioList) {

        for (int i = 0; i < inventarioList.size(); i++) {
            inventarioList.get(i).setIdRebelde(idRebelde);
            Optional<Item> itemOptional = itemService.buscarItemPorNome(inventarioList.get(i).getNomeItem());

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
            Optional<Item> itemOptional = itemService.buscarItemPorId(inventarioList.get(i).getIdItem());

            if (itemOptional.isPresent()) {
                pontuacao += (itemOptional.get().getPontos() * inventarioList.get(i).getQuantidade());
            }
        }

        return pontuacao;
    }

    public void dividirItensNegociacao(long idRebelde1, long idRebelde2, List<Inventario> inventarioList) {

        for (int i = 0; i < inventarioList.size(); i++) {
            // Dividindo os itens da negociação em duas listas, uma para cada id de Rebelde
            if (inventarioList.get(i).getIdRebelde() == idRebelde1) {
                inventarioNegociante1.add(inventarioList.get(i));
            } else {
                if (inventarioList.get(i).getIdRebelde() == idRebelde2) {
                    inventarioNegociante2.add(inventarioList.get(i));
                } else {
                    throw new RuntimeException("Item não pertence aos rebeldes da negociação");
                }
            }
        }

        validarPontuacoes(inventarioNegociante1, inventarioNegociante2);
        atualizarInventarios(idRebelde1, idRebelde2, inventarioNegociante1, inventarioNegociante2);
    }

    public void validarPontuacoes(List<Inventario> inventarioNegociante1, List<Inventario> inventarioNegociante2) {
        int pontuacaoInventario1 = calcularPontuacao(inventarioNegociante1);
        int pontuacaoInventario2 = calcularPontuacao(inventarioNegociante2);

        if (pontuacaoInventario1 != pontuacaoInventario2) {
            throw new RuntimeException("Negociação não permitida. Número de pontos não é exatamente igual");
        }
    }

    public void atualizarInventarios(long idRebelde1, long idRebelde2, List<Inventario> inventarioNegociante1, List<Inventario> inventarioNegociante2) {

        for (int i = 0; i < inventarioNegociante1.size(); i++) {
            inventarioNegociante1.get(i).setIdRebelde(idRebelde2);
            inventarioRepository.save(inventarioNegociante1.get(i));
        }

        for (int i = 0; i < inventarioNegociante2.size(); i++) {
            inventarioRepository.save(inventarioNegociante2.get(i));
        }
    }
}
