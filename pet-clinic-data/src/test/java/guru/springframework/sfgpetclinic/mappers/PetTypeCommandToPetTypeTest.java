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
class PetTypeCommandToPetTypeTest {

	PetTypeCommandToPetType converter;

    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final String NAME = "name";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		converter = new PetTypeCommandToPetType();
	}
	
    @Test
    void testNullObject() throws Exception {
    	Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
    	Assertions.assertNotNull(converter.convert(new PetTypeCommand()));
    }
    
	@Test
	void testConvert() {
		//given
		PetTypeCommand petTypeCommand = new PetTypeCommand();
		petTypeCommand.setId(ID_VALUE);
		petTypeCommand.setName(NAME);
        
		PetType petType = converter.convert(petTypeCommand);

		//then
		Assertions.assertEquals(ID_VALUE, petType.getId());
		Assertions.assertEquals(NAME, petType.getName());
	}
}
