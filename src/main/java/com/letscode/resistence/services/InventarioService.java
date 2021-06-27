package com.letscode.resistence.services;

import com.letscode.resistence.mappers.MapperInventario;
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

    private MapperInventario mapperInventario;

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
        List<Inventario> inventarioNegociante1 = null, inventarioNegociante2 = null;
        mapperInventario = new MapperInventario();

        for (int i = 0; i < inventarioList.size(); i++) {
            Optional<Inventario> inventarioOptional = inventarioRepository
                    .findByIdAndIdRebelde(inventarioList.get(i).getId(), inventarioList.get(i).getIdRebelde());
            Inventario inventarioObjeto = mapperInventario.converterInventario(inventarioOptional);

            // Dividindo os itens da negociação em duas listas, uma para cada id de Rebelde
            if (inventarioObjeto.getIdRebelde() == idRebelde1) {
                inventarioNegociante1.add(inventarioObjeto);
            } else {
                if (inventarioObjeto.getIdRebelde() == idRebelde2) {
                    inventarioNegociante2.add(inventarioObjeto);
                } else {
                    throw new RuntimeException("Item não pertence aos rebeldes da negociação");
                }
            }
        }

        validarPontuacoes(idRebelde1, idRebelde2,inventarioNegociante1, inventarioNegociante2);
    }

    public void validarPontuacoes(long idRebelde1, long idRebelde2, List<Inventario> inventarioNegociante1, List<Inventario> inventarioNegociante2) {
        int pontuacaoInventario1 = calcularPontuacao(inventarioNegociante1);
        int pontuacaoInventario2 = calcularPontuacao(inventarioNegociante2);

        if (pontuacaoInventario1 != pontuacaoInventario2) {
            throw new RuntimeException("Negociação não permitida. Número de pontos não é exatamente igual");
        }

        atualizarInventarios(idRebelde1, idRebelde2, inventarioNegociante1, inventarioNegociante2);
    }

    public void atualizarInventarios(long idRebelde1, long idRebelde2, List<Inventario> inventarioNegociante1, List<Inventario> inventarioNegociante2) {

        for (int i = 0; i < inventarioNegociante1.size(); i++) {
            inventarioNegociante1.get(i).setIdRebelde(idRebelde2);
        }

        for (int i = 0; i < inventarioNegociante2.size(); i++) {
            inventarioNegociante2.get(i).setIdRebelde(idRebelde1);
        }

        List<Inventario> inventarioCadasterado1 = cadastrarInventario(idRebelde2, inventarioNegociante1);
        List<Inventario> inventarioCadasterado2 = cadastrarInventario(idRebelde1, inventarioNegociante2);

    }
}
