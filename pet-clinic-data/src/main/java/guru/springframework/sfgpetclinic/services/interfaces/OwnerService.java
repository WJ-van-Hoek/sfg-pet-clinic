/**
 * 
 */
package guru.springframework.sfgpetclinic.services.interfaces;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.model.Owner;

/**
 * @author Hoek0024 on 14 feb. 2023
 *
 */
public interface OwnerService extends PersonService<Owner> {

	OwnerCommand findCommandById(Long id);
	OwnerCommand saveOwnerCommandAsEntity(OwnerCommand ownerCommand);

}
