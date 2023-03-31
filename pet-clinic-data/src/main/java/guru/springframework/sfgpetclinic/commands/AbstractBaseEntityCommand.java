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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractBaseEntityCommand {
	private Long id;
	
	public boolean isNew() {
		return this.id == null;
	}
}
