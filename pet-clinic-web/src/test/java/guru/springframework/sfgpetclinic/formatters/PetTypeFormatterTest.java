/**
 * 
 */
package guru.springframework.sfgpetclinic.formatters;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.interfaces.PetTypeService;

/**
 * @author Hoek0024 on 12 apr. 2023
 *
 */
class PetTypeFormatterTest {
	private static final String DEFAULT_PET_TYPE_NAME = "Cat";
	private static final PetType DEFAULT_PET_TYPE = PetType.builder().id(1l).name(DEFAULT_PET_TYPE_NAME).build();
	private Set<PetType> petTypes;

	@Mock
	PetTypeService petTypeService;

	@InjectMocks
	PetTypeFormatter petTypeFormatter;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		petTypes = new HashSet<>();
		petTypes.add(DEFAULT_PET_TYPE);
		petTypes.add(PetType.builder().id(1l).name("Dog").build());
	}

	/**
	 * Test method for
	 * {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#print(guru.springframework.sfgpetclinic.model.PetType, java.util.Locale)}.
	 */
	@Test
	void testPrintHappy() {
		PetType petType = PetType.builder().id(1l).name(DEFAULT_PET_TYPE_NAME).build();
		Locale locale = Locale.ENGLISH;
		String printed = petTypeFormatter.print(petType, locale);

		Assertions.assertEquals(DEFAULT_PET_TYPE_NAME, printed);
	}

	/**
	 * Test method for
	 * {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#print(guru.springframework.sfgpetclinic.model.PetType, java.util.Locale)}.
	 */
	@Test
	void testPrintNullName() {
		PetType petType = PetType.builder().id(1l).build();
		Locale locale = Locale.ENGLISH;
		String printed = petTypeFormatter.print(petType, locale);

		Assertions.assertNull(printed);
	}

	/**
	 * Test method for
	 * {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#print(guru.springframework.sfgpetclinic.model.PetType, java.util.Locale)}.
	 */
	@Test
	void testPrintNullLocale() {
		// given
		Locale locale = null;
		String printed = petTypeFormatter.print(DEFAULT_PET_TYPE, locale);

		Assertions.assertEquals(DEFAULT_PET_TYPE_NAME, printed);
	}

	/**
	 * Test method for
	 * {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#parse(java.lang.String, java.util.Locale)}.
	 */
	@Test
	void testParseHappy() throws Exception {
		// given
		Locale locale = Locale.ENGLISH;

		when(petTypeService.findAll()).thenReturn(petTypes);

		// when
		PetType parsed = petTypeFormatter.parse(DEFAULT_PET_TYPE_NAME, locale);

		// then
		Assertions.assertEquals(DEFAULT_PET_TYPE, parsed);
	}

	/**
	 * Test method for {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#parse(java.lang.String, java.util.Locale)}.
	 */
	@Test
	void testParseNotExistingPetTypeString() throws Exception {
		String notExistingPetTypeName = "Rabbit";
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		//then
		ParseException thrown = Assertions.assertThrows(ParseException.class, () -> petTypeFormatter.parse(notExistingPetTypeName, Locale.ENGLISH));
		assertTrue(thrown.getMessage().contentEquals("type not found: " + notExistingPetTypeName));
	}
	
	/**
	 * Test method for {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#parse(java.lang.String, java.util.Locale)}.
	 */
	@Test
	void testParseNullString() throws Exception {
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		//then
		ParseException thrown = Assertions.assertThrows(ParseException.class, () -> petTypeFormatter.parse(null, Locale.ENGLISH));
		assertTrue(thrown.getMessage().contentEquals("type not found: " + null));
	}
	
	/**
	 * Test method for {@link guru.springframework.sfgpetclinic.formatters.PetTypeFormatter#parse(java.lang.String, java.util.Locale)}.
	 */
	@Test
	void testParseNullLocale() throws Exception {
		when(petTypeService.findAll()).thenReturn(petTypes);

		// when
		PetType parsed = petTypeFormatter.parse(DEFAULT_PET_TYPE_NAME, null);

		// then
		Assertions.assertEquals(DEFAULT_PET_TYPE, parsed);
	}
}
