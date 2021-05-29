package com.miguel.morales.ecommers.api.crud;

import java.util.List;
import java.util.Optional;

public interface HibernateService<T, C, U> {
    public List<T> getAll();

    public Optional<T> getOne(Long id);

    public T create(C item);

    public T update(U item, Long id);

    public int delete(Long id);
}
