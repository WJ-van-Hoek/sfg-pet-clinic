/**
 * 
 */
package guru.springframework.sfgpetclinic.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Component
public class OwnerToOwnerCommand implements Converter<Owner, OwnerCommand> {

	private final PetToPetCommand petConverter;

	/**
	 * @param petConverter
	 */
	public OwnerToOwnerCommand(PetToPetCommand petConverter) {
		this.petConverter = petConverter;
	}

	@Override
	public OwnerCommand convert(Owner source) {
		if (source == null) {
			return null;
		}

		final OwnerCommand command = new OwnerCommand();
		command.setId(source.getId());
		command.setFirstName(source.getFirstName());
		command.setLastName(source.getLastName());
		command.setAddress(source.getAddress());
		command.setCity(source.getCity());
		command.setTelephone(source.getTelephone());
		if (source.getPets() != null && source.getPets().size() > 0) {
			source.getPets().forEach((Pet pet) -> command.getPetCommands().add(petConverter.convert(pet)));
		}
		return command;
	}

}
