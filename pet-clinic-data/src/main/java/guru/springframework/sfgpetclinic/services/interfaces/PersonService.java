/**
 * 
 */
package guru.springframework.sfgpetclinic.services.interfaces;

import java.util.List;

import guru.springframework.sfgpetclinic.model.AbstractPerson;

/**
 * @author Hoek0024 on 17 feb. 2023
 *
 */
public interface PersonService<T extends AbstractPerson> extends BaseEntityService<T>{

	T findByFirstName(String firstName);
	
	T findByLastName(String lastName);
	
	List<T> findAllByLastNameLike(String lastName);
}
