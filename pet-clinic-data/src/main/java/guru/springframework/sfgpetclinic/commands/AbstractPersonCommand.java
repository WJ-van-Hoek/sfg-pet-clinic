/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractPersonCommand extends AbstractBaseEntityCommand {
	String firstName;
	String lastName;
	
	/**
	 * @param id
	 * @param firstName2
	 * @param lastName2
	 */
	protected AbstractPersonCommand(Long id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
