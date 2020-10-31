package ru.ITLab.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);

    void save(T enity);
    void update(T enity);
    void deleteById(Long id);
    void delete(T enity);


}
