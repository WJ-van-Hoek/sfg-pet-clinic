/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class PetTypeCommand extends AbstractBaseEntityCommand {
	@Builder
	public PetTypeCommand(Long id, String name) {
		super(id);
		this.name = name;
	}

	private String name;

	public String toString() {
		return getName();
	}
}
