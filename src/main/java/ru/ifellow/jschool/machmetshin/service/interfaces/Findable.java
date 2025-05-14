package ru.ifellow.jschool.machmetshin.service.interfaces;

import java.util.List;
import java.util.Optional;

// Буковки в дженериках можно задавать любые. Даже не только буковки, но и целые наборы букв.
// Это помогает намекнуть другим разработчикам-пользователям этого кода, что именно за значения предполагаются.
// Например, под K и V обычно имеются в виду key и value (и это не наш случай).
// Тут уместнее написать Findable<ID, E>, где ID - это айди, а E - это entity.
// Ну и микропридирка по названию Findable - это как будто что-то, что можно поискать.
// А тут все же сервисы, которые умеют искать. На мой вкус, правильнее Finder.
public interface Findable<K,V> {

    Optional<V> findById(K id);

    List<V> findAll();
}
