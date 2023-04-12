/**
 * 
 */
package guru.springframework.sfgpetclinic.services.map_implementation;

import java.util.Collection;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;
import guru.springframework.sfgpetclinic.services.interfaces.PetTypeService;

/**
 * @author Hoek0024 on 15 feb. 2023
 *
 */
@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractBaseEntityMapService<Pet> implements PetService {

	private final PetTypeService petTypeService;

	/**
	 * @param petTypeService
	 */
	public PetMapService(PetTypeService petTypeService) {
		this.petTypeService = petTypeService;
	}

	@Override
	public Pet save(Pet pet) {
		nullCheck(pet);

		PetType petType = pet.getPetType();
		petTypeService.nullCheck(petType);

		if (petType.getId() == null) {
			pet.setPetType(petTypeService.save(pet.getPetType()));
		}

		return super.save(pet);
	}

	@Override
	public PetCommand findCommandById(Long id) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<PetCommand> findAllPetCommandsOfOwner(Long ownerId) {
		
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PetType findPetType(Long petId) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetCommand savePetCommandAsEntity(PetCommand petCommand) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Pet> findAllPetsOfOwner(Long ownerId) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
