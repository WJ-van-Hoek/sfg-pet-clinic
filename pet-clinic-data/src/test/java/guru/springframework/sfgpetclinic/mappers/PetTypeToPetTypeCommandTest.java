/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.model.PetType;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
class PetTypeToPetTypeCommandTest {

	PetTypeToPetTypeCommand converter;

    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final String NAME = "name";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		converter = new PetTypeToPetTypeCommand();
	}
	
    @Test
    void testNullObject() throws Exception {
    	Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
    	Assertions.assertNotNull(converter.convert(new PetType()));
    }
    
	@Test
	void testConvert() {
		//given
		PetType petType = new PetType();
		petType.setId(ID_VALUE);
		petType.setName(NAME);
        
		PetTypeCommand petTypeCommand = converter.convert(petType);

		//then
		Assertions.assertEquals(ID_VALUE, petTypeCommand.getId());
		Assertions.assertEquals(NAME, petTypeCommand.getName());
	}
}
