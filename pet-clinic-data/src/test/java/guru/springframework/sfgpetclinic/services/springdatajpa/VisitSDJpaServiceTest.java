/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.mappers.PetCommandToPet;
import guru.springframework.sfgpetclinic.mappers.VisitCommandToVisit;
import guru.springframework.sfgpetclinic.mappers.VisitToVisitCommand;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;

/**
 * @author Hoek0024 on 13 apr. 2023
 *
 */
class VisitSDJpaServiceTest {

	@Mock
	VisitRepository<Visit> visitRepository;
	
	@Mock
	VisitCommandToVisit visitCommandToVisit;
	
	@Mock
	VisitToVisitCommand visitToVisitCommand;
	
	@Mock
	PetCommandToPet petCommandToPet;
	
	@InjectMocks
	VisitSDJpaService<Visit, VisitRepository<Visit>> visitSDJpaService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link guru.springframework.sfgpetclinic.services.springdatajpa.VisitSDJpaService#saveVisitCommandAsEntity(guru.springframework.sfgpetclinic.commands.VisitCommand)}.
	 */
	@Test
	void testSaveVisitCommandAsEntity() {
		Visit visit = Visit.builder().id(2l).build();
		
		VisitCommand visitCommand = VisitCommand.builder().id(2l).build();
		
		Pet pet = Pet.builder().id(1l).build();
		
		when(visitRepository.save(any(Visit.class))).thenReturn(visit);
		when(visitCommandToVisit.convert(any(VisitCommand.class))).thenReturn(visit);
		when(visitToVisitCommand.convert(any(Visit.class))).thenReturn(visitCommand);
		when(petCommandToPet.convert(any(PetCommand.class))).thenReturn(pet);
		
		// when
		VisitCommand savedVisitCommand = visitSDJpaService.saveVisitCommandAsEntity(visitCommand);
		
		assertEquals(visitCommand, savedVisitCommand);
		verify(visitRepository, times(1)).save(any(Visit.class));
		verify(visitCommandToVisit, times(1)).convert(any(VisitCommand.class));
		verify(visitToVisitCommand, times(1)).convert(any(Visit.class));
	}

}
