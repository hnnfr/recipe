package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.mappers.UnitOfMeasureToCommandMapper;
import guru.springframework.recipe.mappers.UnitOfMeasureToCommandMapperImpl;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceTest {

    @Mock
    UnitOfMeasureRepository repository;

    UnitOfMeasureToCommandMapper mapper;

    UnitOfMeasureService unitOfMeasureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new UnitOfMeasureToCommandMapperImpl();
        unitOfMeasureService = new UnitOfMeasureServiceImpl(repository, mapper);
    }

    @Test
    void testGetAllUnitOfMeasures() {
        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        unitOfMeasures.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasures.add(unitOfMeasure2);

        when(repository.findAll()).thenReturn(unitOfMeasures);

        // when
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = unitOfMeasureService.getAllUnitOfMeasures();

        // then
        assertEquals(2, unitOfMeasureCommands.size());
        verify(repository, times(1)).findAll();
//        verify(mapper, times(1)).entitiesToCommands(any());   -- cannot verify here because mapper is not a mock !!
    }
}