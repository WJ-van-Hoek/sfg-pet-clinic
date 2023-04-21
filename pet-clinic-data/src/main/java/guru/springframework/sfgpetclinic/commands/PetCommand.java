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
public class PetCommand extends AbstractBaseEntityCommand {
	private String name;
	private PetType petType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private OwnerCommand ownerCommand;
	private Set<VisitCommand> visitCommands = new HashSet<>();

	/**
	 * @param b
	 * @param name
	 * @param petType
	 * @param birthDate
	 * @param ownerCommand
	 * @param visitCommands
	 */
	@Builder
	public PetCommand(Long id, String name, PetType petType, LocalDate birthDate,
			OwnerCommand ownerCommand, Set<VisitCommand> visitCommands) {
		super(id);
		this.name = name;
		this.petType = petType;
		this.birthDate = birthDate;
		this.ownerCommand = ownerCommand;
		this.visitCommands = new HashSet<>();
	}	
		
	public void addVisitCommand(VisitCommand visitCommand) {
		visitCommand.setPetCommand(this);
		this.getVisitCommands().add(visitCommand);
	}



	

}
