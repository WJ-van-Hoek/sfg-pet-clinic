/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.Visit;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
class VisitCommandToVisitTest {
	
	VisitCommandToVisit converter;

	
    private static final Long ID_VALUE = Long.valueOf(1l);
    private static final LocalDate DATE = LocalDate.of(2023,3,30);
    private static final String DESCRIPTION = "description";
    
    
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		converter = new VisitCommandToVisit();
	}
	
    @Test
    void testNullObject() throws Exception {
    	Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
    	Assertions.assertNotNull(converter.convert(new VisitCommand()));
    }
    
	@Test
	void testConvert() {
		//given
		VisitCommand visitCommand = new VisitCommand();
		visitCommand.setId(ID_VALUE);
		visitCommand.setDescription(DESCRIPTION);
		visitCommand.setDate(DATE);
        
		Visit visit = converter.convert(visitCommand);

		//then
		Assertions.assertEquals(ID_VALUE, visit.getId());
		Assertions.assertEquals(DESCRIPTION, visit.getDescription());
		Assertions.assertEquals(DATE, visit.getDate());
	}
}
