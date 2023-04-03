/**
 * 
 */
package guru.springframework.sfgpetclinic.services.interfaces;

import java.util.Collection;

import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.model.PetType;

/**
 * @author Hoek0024 on 23 feb. 2023
 *
 */
public interface PetTypeService extends BaseEntityService<PetType>{
	PetTypeCommand findCommandById(Long id);
	Collection<PetTypeCommand> findAllPetTypeCommands();
}
