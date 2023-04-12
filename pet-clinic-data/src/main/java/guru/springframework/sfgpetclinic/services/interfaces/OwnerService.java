/**
 * 
 */
package guru.springframework.sfgpetclinic.services.interfaces;

import java.util.Set;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Owner;

/**
 * @author Hoek0024 on 14 feb. 2023
 *
 */
public interface OwnerService extends PersonService<Owner> {

	OwnerCommand findCommandById(Long id);
	Set<PetCommand> findAllPetCommandsOfOwnerId(Long ownerId);
	OwnerCommand saveOwnerCommandAsEntity(OwnerCommand ownerCommand);
	OwnerCommand addNewPetCommandToOwnerCommand(OwnerCommand ownerCommand);
	PetCommand findPetCommandByName(OwnerCommand ownerCommand, String name);
	PetCommand findPetCommandByName(OwnerCommand ownerCommand, String name, boolean ignoreNew);
	void updatePetCommand(PetCommand petCommand);
	void addPetCommand(OwnerCommand ownerCommand, PetCommand petCommand);

}
