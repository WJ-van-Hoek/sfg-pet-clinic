/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import guru.springframework.sfgpetclinic.model.PetType;
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
public class PetCommand extends AbstractBaseEntityCommand {
	private String name;
	private PetType petType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private OwnerCommand ownerCommand;
	@Builder.Default
	private Set<VisitCommand> visitCommands = new HashSet<>();

	public void addVisitCommand(VisitCommand visitCommand) {
		visitCommand.setPetCommand(this);
		this.getVisitCommands().add(visitCommand);
	}
}
