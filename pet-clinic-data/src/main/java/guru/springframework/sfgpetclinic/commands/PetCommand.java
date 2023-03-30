/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
public class PetCommand extends AbstractBaseEntityCommand {
	private String name;
    private PetTypeCommand petTypeCommand;
    private LocalDate birthDate;
    private Set<VisitCommand> visitCommands = new HashSet<>();
}
