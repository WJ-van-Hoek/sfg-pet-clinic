/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Component
public class OwnerCommandToOwner implements Converter<OwnerCommand, Owner> {
	private final PetCommandToPet petConverter;

	/**
	 * @param petConverter
	 */
	public OwnerCommandToOwner(PetCommandToPet petConverter) {
		this.petConverter = petConverter;
	}

	@Override
	public Owner convert(OwnerCommand source) {
		if (source == null) {
			return null;
		}

		final Owner result = new Owner();
		result.setId(source.getId());
		result.setFirstName(source.getFirstName());
		result.setLastName(source.getLastName());
		result.setAddress(source.getAddress());
		result.setCity(source.getCity());
		result.setTelephone(source.getTelephone());
		if (source.getPetCommands() != null && !source.getPetCommands().isEmpty()) {
			source.getPetCommands()
					.forEach((PetCommand petCommand) -> result.getPets().add(petConverter.convert(petCommand)));
			result.getPets().forEach((Pet pet) -> pet.setOwner(result));
		}
		return result;
	}
}
