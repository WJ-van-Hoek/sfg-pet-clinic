/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Visit;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
public class PetToPetCommandTest {
	
	@Mock
	PetTypeToPetTypeCommand petTypeConverter;
	
	@Mock
	VisitToVisitCommand visitConverter;

	@InjectMocks
	PetToPetCommand converter;

    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final String NAME = "name";
    private static final PetTypeCommand PET_TYPE_COMMAND = new PetTypeCommand();
    private Set<Visit> VISITS = new HashSet<>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
    @Test
    void testNullObject() throws Exception {
    	Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
    	Assertions.assertNotNull(converter.convert(new Pet()));
    }
    
	@Test
	void testConvert() {
		//given
		Pet pet = new Pet();
		pet.setId(ID_VALUE);
		pet.setName(NAME);
		pet.setPetType(new PetType());
		pet.setVisits(VISITS);
		pet.getVisits().add(new Visit());

		//when
        when(petTypeConverter.convert(any(PetType.class))).thenReturn(PET_TYPE_COMMAND);
        when(visitConverter.convert(any(Visit.class))).thenReturn(new VisitCommand());
        
		PetCommand petCommand = converter.convert(pet);

		//then
		Assertions.assertEquals(ID_VALUE, petCommand.getId());
		Assertions.assertEquals(NAME, petCommand.getName());
		Assertions.assertEquals(PET_TYPE_COMMAND, petCommand.getPetTypeCommand());

		verify(visitConverter, times(1)).convert(any(Visit.class));
	}
}
