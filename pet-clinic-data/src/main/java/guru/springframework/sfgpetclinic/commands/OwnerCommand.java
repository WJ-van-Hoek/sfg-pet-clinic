/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerCommand extends AbstractPersonCommand {
	private String address;
	private String city;
	private String telephone;
	private Set<PetCommand> petCommands = new HashSet<>();

	public void addPetCommand(PetCommand petCommand) {
		petCommand.setOwnerCommand(this);
		this.getPetCommands().add(petCommand);
	}

	public void removePetCommandById(Long id) {
		this.getPetCommands().removeIf(petCommand -> petCommand.getId() == id);
	}
}
