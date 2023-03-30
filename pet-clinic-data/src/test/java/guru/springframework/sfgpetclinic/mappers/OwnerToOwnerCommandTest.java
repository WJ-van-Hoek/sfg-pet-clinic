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
public class OwnerToOwnerCommandTest {
	
	@Mock
	PetToPetCommand petConverter;

	@InjectMocks
	OwnerToOwnerCommand converter;

    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String TELEPHONE = "123456789";
    private Set<Pet> PET_COMMANDS = new HashSet<>();

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
    	Assertions.assertNotNull(converter.convert(new Owner()));
    }
    
	@Test
	void testConvert() {
		//given
		Owner owner = new Owner();
		owner.setId(ID_VALUE);
		owner.setFirstName(FIRST_NAME);
		owner.setLastName(LAST_NAME);
		owner.setAddress(ADDRESS);
		owner.setCity(CITY);
		owner.setTelephone(TELEPHONE);
		owner.setPets(PET_COMMANDS);

		//when
        when(petConverter.convert(any(Pet.class))).thenReturn(new PetCommand());

		OwnerCommand ownerCommand = converter.convert(owner);

		//then
		Assertions.assertEquals(ID_VALUE, ownerCommand.getId());
		Assertions.assertEquals(FIRST_NAME, ownerCommand.getFirstName());
		Assertions.assertEquals(LAST_NAME, ownerCommand.getLastName());
		Assertions.assertEquals(ADDRESS, ownerCommand.getAddress());
		Assertions.assertEquals(CITY, ownerCommand.getCity());
		Assertions.assertEquals(TELEPHONE, ownerCommand.getTelephone());
		verify(petConverter, times(0)).convert(any(Pet.class));
	}
}
