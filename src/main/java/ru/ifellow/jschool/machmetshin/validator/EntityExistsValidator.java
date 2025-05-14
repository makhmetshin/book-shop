package ru.ifellow.jschool.machmetshin.validator;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ifellow.jschool.machmetshin.service.interfaces.Findable;

import java.util.Optional;


@AllArgsConstructor
@Component
public class EntityExistsValidator {

    // Параметр айди в методе не используется. Либо удалить, либо заиспользовать
    public  <V> V validate(Optional<V> optionalEntity, Integer id, Class clazz)  {

        V entity = optionalEntity
                .orElseThrow(() -> new EntityNotFoundException("There is no such entity of class " + clazz.getSimpleName() +
                        " which should be found with id"));
        return entity;
    }


}
