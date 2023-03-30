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
public class PetCommandToPetTest {
	
	@Mock
	PetTypeCommandToPetType petTypeConverter;
	
	@Mock
	VisitCommandToVisit visitConverter;

	@InjectMocks
	PetCommandToPet converter;

    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final String NAME = "name";
    private static final PetType PET_TYPE = new PetType();
    private Set<VisitCommand> VISIT_COMMANDS = new HashSet<>();

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
    	Assertions.assertNotNull(converter.convert(new PetCommand()));
    }
    
	@Test
	void testConvert() {
		//given
		PetCommand petCommand = new PetCommand();
		petCommand.setId(ID_VALUE);
		petCommand.setName(NAME);
		petCommand.setPetTypeCommand(new PetTypeCommand());
		petCommand.setVisitCommands(VISIT_COMMANDS);
		petCommand.getVisitCommands().add(new VisitCommand());

		//when
        when(petTypeConverter.convert(any(PetTypeCommand.class))).thenReturn(PET_TYPE);
        when(visitConverter.convert(any(VisitCommand.class))).thenReturn(new Visit());
        
		Pet pet = converter.convert(petCommand);

		//then
		Assertions.assertEquals(ID_VALUE, pet.getId());
		Assertions.assertEquals(NAME, pet.getName());
		Assertions.assertEquals(PET_TYPE, pet.getPetType());

		verify(visitConverter, times(1)).convert(any(VisitCommand.class));
	}
}
