package guru.springframework.recipe.mappers;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitOfMeasureToCommandMapper {
    UnitOfMeasure commandToEntity(UnitOfMeasureCommand command);
    UnitOfMeasureCommand entityToCommand(UnitOfMeasure entity);
}
