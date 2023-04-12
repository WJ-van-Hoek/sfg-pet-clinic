/**
 * 
 */
package guru.springframework.sfgpetclinic.services.interfaces;

import java.util.Collection;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;

/**
 * @author Hoek0024 on 14 feb. 2023
 *
 */
public interface PetService extends BaseEntityService<Pet>{

	public PetCommand findCommandById(Long id);
	public Collection<Pet> findAllPetsOfOwner(Long ownerId);
	public Collection<PetCommand> findAllPetCommandsOfOwner(Long ownerId);
	public PetCommand savePetCommandAsEntity(PetCommand petCommand);
	public PetType findPetType(Long petId);
}
