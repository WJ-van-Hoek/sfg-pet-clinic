/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.mappers.PetTypeCommandToPetType;
import guru.springframework.sfgpetclinic.mappers.PetTypeToPetTypeCommand;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

/**
 * @author Hoek0024 on 12 apr. 2023
 *
 */
class PetTypeSDJpaServiceTest {
	private static final Long DEFAULT_ID = Long.valueOf(1l);
	private static final String DEFAULT_PET_TYPE_NAME = "Cat";
	private static final PetType DEFAULT_PET_TYPE = PetType.builder().id(DEFAULT_ID).name(DEFAULT_PET_TYPE_NAME).build();
	
	@Mock
	PetTypeRepository<PetType> petTypeRepository;
	
	@Mock
	PetTypeToPetTypeCommand petTypeToPetTypeCommand;
	
	@Mock
	PetTypeCommandToPetType petTypeCommandToPetType; 

	@InjectMocks
	PetTypeSDJpaService<PetType, PetTypeRepository<PetType>> petTypeSDJpaService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link guru.springframework.sfgpetclinic.services.springdatajpa.PetTypeSDJpaService#findCommandById(java.lang.Long)}.
	 */
	@Test
	void testFindCommandByIdHappy() {
		//given
		PetType petType = PetType.builder().build();
		PetTypeCommand petTypeCommand = PetTypeCommand.builder().id(DEFAULT_ID).name(DEFAULT_PET_TYPE_NAME).build();
		when(petTypeRepository.findById(any(Long.class))).thenReturn(Optional.of(petType));
		when(petTypeToPetTypeCommand.convert(petType)).thenReturn(petTypeCommand);
		
		//when
		PetTypeCommand foundCommand = petTypeSDJpaService.findCommandById(DEFAULT_ID);
		
		//then
		Assertions.assertEquals(petTypeCommand, foundCommand);
		verify(petTypeRepository, times(1)).findById(any(Long.class));
		verify(petTypeToPetTypeCommand, times(1)).convert(petType);
	}

	/**
	 * Test method for {@link guru.springframework.sfgpetclinic.services.springdatajpa.PetTypeSDJpaService#findAllPetTypes()}.
	 */
	@Test
	void testFindAllPetTypesHappy() {
		//given
		Set<PetType> petTypes = new HashSet<>();
		PetType defaultPetType = PetType.builder().id(DEFAULT_ID).name(DEFAULT_PET_TYPE_NAME).build();
		PetType secondPetType = PetType.builder().id(2l).name("Rabbit").build();
		petTypes.add(defaultPetType);
		petTypes.add(secondPetType);
		when(petTypeRepository.findAll()).thenReturn(petTypes);
		
		//when
		Set<PetType> foundPetTypes = petTypeSDJpaService.findAll();
		
		//then
		Assertions.assertEquals(petTypes, foundPetTypes);
		verify(petTypeRepository, times(1)).findAll();
	}

}
