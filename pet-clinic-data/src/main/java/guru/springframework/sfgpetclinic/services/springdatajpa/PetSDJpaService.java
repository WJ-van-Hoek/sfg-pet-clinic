/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.mappers.PetCommandToPet;
import guru.springframework.sfgpetclinic.mappers.PetToPetCommand;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;

/**
 * @author Hoek0024 on 14 mrt. 2023
 *
 */
@Service
@Profile("jpa")
public class PetSDJpaService<T extends Pet, R extends PetRepository<Pet>>
extends AbstractBaseEntitySDJpaService<Pet, R> implements PetService {
	
	PetToPetCommand petToPetCommand;
	PetCommandToPet petCommandToPet;
	
	OwnerService ownerService;
	
	/**
	 * @param repository
	 */
	public PetSDJpaService(R repository, OwnerService ownerService, PetToPetCommand petToPetCommand, PetCommandToPet petCommandToPet) {
		super(repository);
		this.ownerService = ownerService;
		this.petToPetCommand = petToPetCommand;
		this.petCommandToPet = petCommandToPet;
	}

	
	public PetCommand findCommandById(Long id) {
		return toCommand(findById(id));
	}
	
	public Collection<PetCommand> findAllPetCommandsOfOwner(Long ownerId) {
		Collection<PetCommand> petCommands = new HashSet<>();
		ownerService.findById(ownerId).getPets().forEach(pet -> petCommands.add(toCommand(pet)));
		return petCommands;
	}
	
	@Override
	public PetType findPetType(Long petId) {
		return findCommandById(petId).getPetType();
	}
	
	@Override
	public PetCommand savePetCommandAsEntity(PetCommand petCommand) {
		Pet pet = toEntity(petCommand);
		return toCommand(repository.save(pet));
	}
	
	private PetCommand toCommand(Pet pet) {
		return petToPetCommand.convert(pet);
	}

	private Pet toEntity(PetCommand petCommand) {
		return petCommandToPet.convert(petCommand);
	}

	@Override
	public Collection<Pet> findAllPetsOfOwner(Long ownerId) {
		Collection<Pet> pets = new HashSet<>();
		ownerService.findById(ownerId).getPets().forEach(pets::add);
		return pets;
	}
}
