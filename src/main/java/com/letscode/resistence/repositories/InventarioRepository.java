package com.letscode.resistence.repositories;

import com.letscode.resistence.models.Inventario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventarioRepository extends CrudRepository<Inventario, Long> {
    Optional<Inventario> findByIdAndIdRebelde(long id, long idRebelde);
}
