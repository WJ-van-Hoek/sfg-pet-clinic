/**
 * 
 */
package guru.springframework.sfgpetclinic.services.map_implementation;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hoek0024 on 15 feb. 2023
 *
 */
@Slf4j
@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractPersonMapService<Owner> implements OwnerService {

	private final PetService petService;

	/**
	 * @param petTypeService
	 * @param petService
	 */
	public OwnerMapService(PetService petService) {
		this.petService = petService;
	}

	@Override
	public Owner save(Owner owner) {
		if (owner == null) {
			return null;
		}
				
		owner.getPets().forEach(pet -> petService.save(pet));
		return super.save(owner);
	}

	@Override
	public OwnerCommand findCommandById(Long id) {
		log.error("findCommandById is not implemented in OwnerMapService");
		return null;
	}

	@Override
	public OwnerCommand saveOwnerCommandAsEntity(OwnerCommand ownerCommand) {
		log.error("saveOwnerCommand is not implemented in OwnerMapService");
		return null;
	}

	@Override
	public OwnerCommand addNewPetCommandToOwnerCommand(OwnerCommand ownerCommand) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetCommand findPetCommandByName(OwnerCommand ownerCommand, String name) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetCommand findPetCommandByName(OwnerCommand ownerCommand, String name, boolean ignoreNew) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PetCommand> findAllPetCommandsOfOwnerId(Long ownerId) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePetCommand(PetCommand petCommand) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPetCommand(OwnerCommand ownerCommand, PetCommand petCommand) {
		
		// TODO Auto-generated method stub
		
	}

}
