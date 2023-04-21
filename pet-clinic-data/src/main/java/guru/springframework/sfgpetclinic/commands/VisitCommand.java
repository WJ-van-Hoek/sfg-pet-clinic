/**
 * 
 */
package guru.springframework.sfgpetclinic.commands;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
public class VisitCommand extends AbstractBaseEntityCommand {
	private PetCommand petCommand;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String description;
	
	/**
	 * @param b
	 * @param petCommand
	 * @param date
	 * @param description
	 */
	@Builder
	public VisitCommand(Long id, PetCommand petCommand, LocalDate date, String description) {
		super(id);
		this.petCommand = petCommand;
		this.date = date;
		this.description = description;
	}
}
