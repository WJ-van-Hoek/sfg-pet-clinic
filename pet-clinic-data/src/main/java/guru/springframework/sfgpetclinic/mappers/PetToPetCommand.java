/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
public class PetToPetCommand implements Converter<Pet, PetCommand> {

	PetTypeToPetTypeCommand petTypeConverter;
	VisitToVisitCommand visitConverter;

	/**
	 * @param petTypeConverter
	 */
	public PetToPetCommand(PetTypeToPetTypeCommand petTypeConverter, VisitToVisitCommand visitConverter) {
		this.petTypeConverter = petTypeConverter;
		this.visitConverter = visitConverter;
	}

	@Override
	public PetCommand convert(Pet source) {
		if (source == null) {
			return null;
		}

		final PetCommand result = new PetCommand();
		result.setId(source.getId());
		result.setName(source.getName());
		result.setBirthDate(source.getBirthDate());
		result.setPetTypeCommand(petTypeConverter.convert(source.getPetType()));
		if (source.getVisits() != null && !source.getVisits().isEmpty()) {
			source.getVisits().forEach((Visit visit) -> result.getVisitCommands().add(visitConverter.convert(visit)));
		}
		return result;
	}

}
