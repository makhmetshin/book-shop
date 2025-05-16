package ru.ifellow.jschool.machmetshin.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface Finder<ID,E> {

    Optional<E> findById(ID id);

    List<E> findAll();
}
