package com.letscode.resistence.repositories;

import com.letscode.resistence.models.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findByNome(String nome);
}