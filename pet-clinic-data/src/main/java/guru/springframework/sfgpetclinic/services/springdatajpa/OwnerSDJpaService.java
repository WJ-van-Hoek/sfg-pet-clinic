/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.mappers.OwnerCommandToOwner;
import guru.springframework.sfgpetclinic.mappers.OwnerToOwnerCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;

/**
 * @author Hoek0024 on 14 mrt. 2023
 *
 */
@Service
@Profile("jpa")
public class OwnerSDJpaService<T extends Owner, R extends OwnerRepository<Owner>>
		extends AbstractPersonSDJpaService<Owner, R> implements OwnerService {

	OwnerToOwnerCommand ownerToOwnerCommand;
	OwnerCommandToOwner ownerCommandToOwner;

	/**
	 * @param repository
	 */
	public OwnerSDJpaService(R repository, OwnerToOwnerCommand ownerToOwnerCommand,
			OwnerCommandToOwner ownerCommandToOwner) {
		super(repository);
		this.ownerToOwnerCommand = ownerToOwnerCommand;
		this.ownerCommandToOwner = ownerCommandToOwner;
	}

	@Override
	public OwnerCommand findCommandById(Long id) {
		return toCommand(findById(id));
	}

	@Override
	public Set<PetCommand> findAllPetCommandsOfOwnerId(Long ownerId) {
		OwnerCommand ownerCommand = findCommandById(ownerId);
		return ownerCommand.getPetCommands();
	}

	@Override
	public OwnerCommand saveOwnerCommandAsEntity(OwnerCommand ownerCommand) {
		return toCommand(repository.save(toEntity(ownerCommand)));
	}

	@Override
	public OwnerCommand addNewPetCommandToOwnerCommand(OwnerCommand ownerCommand) {
		PetCommand petCommand = new PetCommand();
		ownerCommand.addPetCommand(petCommand);
		return ownerCommand;
	}

	@Override
	public PetCommand findPetCommandByName(OwnerCommand ownerCommand, String name) {
		return findPetCommandByName(ownerCommand, name, false);
	}

	@Override
	public PetCommand findPetCommandByName(OwnerCommand ownerCommand, String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (PetCommand petCommand : ownerCommand.getPetCommands()) {

			String compName = petCommand.getName();
			compName = compName.toLowerCase();
			if (compName.equals(name)) {
				return petCommand;
			}

		}
		return null;
	}

	@Override
	public void addPetCommand(OwnerCommand ownerCommand, PetCommand petCommand) {
		ownerCommand.addPetCommand(petCommand);
	}

	@Override
	public void updatePetCommand(PetCommand petCommand) {
		OwnerCommand ownerCommand = petCommand.getOwnerCommand();
		if (ownerCommand.getPetCommands().removeIf(p -> p.getId() == petCommand.getId())) {
			addPetCommand(ownerCommand, petCommand);
		} else {
			throw new RuntimeException(
					"PetCommand " + petCommand.toString() + " can't be updated because it does not exist");
		}
	}

	private OwnerCommand toCommand(Owner owner) {
		return ownerToOwnerCommand.convert(owner);
	}

	private Owner toEntity(OwnerCommand ownerCommand) {
		return ownerCommandToOwner.convert(ownerCommand);
	}

}
