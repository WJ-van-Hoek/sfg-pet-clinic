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
class VisitToVisitCommandTest {
	VisitToVisitCommand converter;

	private static final Long ID_VALUE = Long.valueOf(1l);
	private static final LocalDate DATE = LocalDate.of(2023, 3, 30);
	private static final String DESCRIPTION = "description";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		converter = new VisitToVisitCommand();
	}

	@Test
	void testNullObject() throws Exception {
		Assertions.assertNull(converter.convert(null));
	}

	@Test
	void testEmptyObject() throws Exception {
		Assertions.assertNotNull(converter.convert(new Visit()));
	}

	@Test
	void testConvert() {
		// given
		Visit visit = new Visit();
		visit.setId(ID_VALUE);
		visit.setDescription(DESCRIPTION);
		visit.setDate(DATE);

		// when
		VisitCommand visitCommand = converter.convert(visit);

		// then
		Assertions.assertEquals(ID_VALUE, visitCommand.getId());
		Assertions.assertEquals(DESCRIPTION, visitCommand.getDescription());
		Assertions.assertEquals(DATE, visitCommand.getDate());
	}
}
