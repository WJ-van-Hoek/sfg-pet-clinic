/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.mappers.PetTypeCommandToPetType;
import guru.springframework.sfgpetclinic.mappers.PetTypeToPetTypeCommand;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.interfaces.PetTypeService;

/**
 * @author Hoek0024 on 14 mrt. 2023
 *
 */
@Service
@Profile("jpa")
public class PetTypeSDJpaService<T extends PetType, R extends PetTypeRepository<PetType>>
		extends AbstractBaseEntitySDJpaService<PetType, R> implements PetTypeService {

	PetTypeCommandToPetType petTypeCommandToPetType;
	PetTypeToPetTypeCommand petTypeToPetTypeCommand;

	/**
	 * @param repository
	 * @param petTypeCommandToPetType
	 * @param petTypeToPetTypeCommand
	 */
	public PetTypeSDJpaService(R repository, PetTypeCommandToPetType petTypeCommandToPetType,
			PetTypeToPetTypeCommand petTypeToPetTypeCommand) {
		super(repository);
		this.petTypeCommandToPetType = petTypeCommandToPetType;
		this.petTypeToPetTypeCommand = petTypeToPetTypeCommand;
	}

	@Override
	public PetTypeCommand findCommandById(Long id) {
		return toCommand(findById(id));
	}

	@Override
	public Collection<PetType> findAllPetTypes() {
		Collection<PetType> petTypes = new HashSet<>();
		repository.findAll().forEach(petType -> petTypes.add(petType));
		return petTypes;
	}
	
	private PetTypeCommand toCommand(PetType petType) {
		return petTypeToPetTypeCommand.convert(petType);
	}

}
