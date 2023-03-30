/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hoek0024 on 30 mrt. 2023
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractPersonCommand extends AbstractBaseEntityCommand {
	String firstName;
	String lastName;
}
