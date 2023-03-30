/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.Pet;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
public class PetCommandToPet implements Converter<PetCommand, Pet> {

	PetTypeCommandToPetType petTypeConverter;
	VisitCommandToVisit visitConverter;

	/**
	 * @param petTypeConverter
	 */
	public PetCommandToPet(PetTypeCommandToPetType petTypeConverter, VisitCommandToVisit visitConverter) {
		this.petTypeConverter = petTypeConverter;
		this.visitConverter = visitConverter;
	}

	@Override
	public Pet convert(PetCommand source) {
		if (source == null) {
			return null;
		}

		final Pet result = new Pet();
		result.setId(source.getId());
		result.setName(source.getName());
		result.setBirthDate(source.getBirthDate());
		result.setPetType(petTypeConverter.convert(source.getPetTypeCommand()));
		if (source.getVisitCommands() != null && !source.getVisitCommands().isEmpty()) {
			source.getVisitCommands().forEach(
					(VisitCommand visitCommand) -> result.getVisits().add(visitConverter.convert(visitCommand)));
		}
		return result;
	}
}
