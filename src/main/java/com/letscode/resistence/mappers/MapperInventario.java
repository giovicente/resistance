package com.letscode.resistence.mappers;

import com.letscode.resistence.models.Inventario;

import java.util.Optional;

public class MapperInventario {

    public Inventario converterInventario(Optional<Inventario> inventarioOptional) {
        Inventario inventario = new Inventario();

        inventario.setId(inventarioOptional.get().getId());
        inventario.setIdItem(inventarioOptional.get().getIdItem());
        inventario.setIdRebelde(inventarioOptional.get().getIdRebelde());
        inventario.setNomeItem(inventarioOptional.get().getNomeItem());
        inventario.setQuantidade(inventarioOptional.get().getQuantidade());

        return inventario;
    }

}
