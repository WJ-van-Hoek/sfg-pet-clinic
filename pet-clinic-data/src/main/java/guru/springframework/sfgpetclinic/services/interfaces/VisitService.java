/**
 * 
 */
package guru.springframework.sfgpetclinic.services.interfaces;

import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.Visit;

/**
 * @author Hoek0024 on 15 mrt. 2023
 *
 */
public interface VisitService extends BaseEntityService<Visit> {
	VisitCommand saveVisitCommandAsEntity(VisitCommand visitCommand);
}
