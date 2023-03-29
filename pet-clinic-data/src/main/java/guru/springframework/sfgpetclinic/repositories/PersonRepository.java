/**
 * 
 */
package guru.springframework.sfgpetclinic.repositories;

import java.util.List;

import guru.springframework.sfgpetclinic.model.AbstractPerson;

/**
 * @author Hoek0024 on 14 mrt. 2023
 *
 */
public interface PersonRepository<T extends AbstractPerson> extends BaseEntityRepository<T> {

	T findByFirstName(String firstName);

	T findByLastName(String firstName);
	
	List<T> findAllByLastNameLike(String lastName);
}
