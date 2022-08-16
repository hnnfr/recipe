package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.mappers.UnitOfMeasureToCommandMapper;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository repository;
    private final UnitOfMeasureToCommandMapper mapper;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository repository, UnitOfMeasureToCommandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAllUnitOfMeasures() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        repository.findAll().forEach(unitOfMeasures::add);
        return mapper.entitiesToCommands(unitOfMeasures);
    }
}
