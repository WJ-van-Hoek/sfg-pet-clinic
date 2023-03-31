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

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
class OwnerCommandToOwnerTest {
	
	@Mock
	PetCommandToPet petConverter;

	@InjectMocks
	OwnerCommandToOwner converter;

    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String TELEPHONE = "123456789";
    private Set<PetCommand> PET_COMMANDS = new HashSet<>();

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
    	Assertions.assertNotNull(converter.convert(new OwnerCommand()));
    }
    
	@Test
	void testConvert() {
		//given
		OwnerCommand ownerCommand = new OwnerCommand();
		ownerCommand.setId(ID_VALUE);
		ownerCommand.setFirstName(FIRST_NAME);
		ownerCommand.setLastName(LAST_NAME);
		ownerCommand.setAddress(ADDRESS);
		ownerCommand.setCity(CITY);
		ownerCommand.setTelephone(TELEPHONE);
		ownerCommand.setPetCommands(PET_COMMANDS);

		//when
        when(petConverter.convert(any(PetCommand.class))).thenReturn(new Pet());

		Owner owner = converter.convert(ownerCommand);

		//then
		Assertions.assertEquals(ID_VALUE, owner.getId());
		Assertions.assertEquals(FIRST_NAME, owner.getFirstName());
		Assertions.assertEquals(LAST_NAME, owner.getLastName());
		Assertions.assertEquals(ADDRESS, owner.getAddress());
		Assertions.assertEquals(CITY, owner.getCity());
		Assertions.assertEquals(TELEPHONE, owner.getTelephone());
		verify(petConverter, times(0)).convert(any(PetCommand.class));
	}

}
