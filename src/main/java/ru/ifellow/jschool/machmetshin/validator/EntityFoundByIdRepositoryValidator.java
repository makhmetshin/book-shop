package ru.ifellow.jschool.machmetshin.validator;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;

@AllArgsConstructor //есть ли смысл в объявлении этого конструктора?
@Component
public class EntityFoundByIdRepositoryValidator {

    // Если честно, что метод validate на самом деле не только валидирует, но еще и ищет и возвращает сущность из БД
    public <K, V> V validate(JpaRepository<V, K> jpaRepository, K entityId, Class<V> clazz)  {

        Object rawEntity = jpaRepository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("There is no such entity which should be found with" +
                        jpaRepository.getClass().getSimpleName() + "and by id " + entityId));

        return clazz.cast(rawEntity);
    }
}
