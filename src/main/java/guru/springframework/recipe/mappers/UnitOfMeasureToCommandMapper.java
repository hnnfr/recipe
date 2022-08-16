package guru.springframework.recipe.mappers;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UnitOfMeasureToCommandMapper {
    UnitOfMeasure commandToEntity(UnitOfMeasureCommand command);
    UnitOfMeasureCommand entityToCommand(UnitOfMeasure entity);

    Set<UnitOfMeasure> commandsToEntities(Set<UnitOfMeasureCommand> commands);
    Set<UnitOfMeasureCommand> entitiesToCommands(Set<UnitOfMeasure> entities);
}
