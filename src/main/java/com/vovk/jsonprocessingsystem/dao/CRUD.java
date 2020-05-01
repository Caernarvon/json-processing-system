package com.vovk.jsonprocessingsystem.dao;

import java.util.Optional;

/**
 * @author Dmytro Vovk
 */

public interface CRUD <E> {

    String create(E entity);

    void update(E entity);

    void delete(String id);

    E findById(String id);
}
