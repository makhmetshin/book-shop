package ru.ifellow.jschool.machmetshin.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface Findable <K,V> {

    Optional<V>  findById(K id);

    List<V> findAll();
}
