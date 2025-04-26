package ru.ifellow.jschool.machmetshin.validator;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;


@AllArgsConstructor
@Component
public class EntityFoundByIdServiceValidator {

    public <K, V> V validate(Findable<K, V> findableById, K entityId, Class<V> clazz)  {

        Object rawEntity = findableById.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException("There is no such entity which should be found with" +
                        findableById.getClass().getSimpleName() + "and by id " + entityId));

        return clazz.cast(rawEntity);
    }


}
