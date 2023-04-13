/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.mappers.PetCommandToPet;
import guru.springframework.sfgpetclinic.mappers.VisitCommandToVisit;
import guru.springframework.sfgpetclinic.mappers.VisitToVisitCommand;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.interfaces.VisitService;

/**
 * @author Hoek0024 on 14 mrt. 2023
 *
 */
@Service
@Profile("jpa")
public class VisitSDJpaService<T extends Visit, R extends VisitRepository<Visit>>
		extends AbstractBaseEntitySDJpaService<Visit, R> implements VisitService {

	VisitToVisitCommand visitToVisitCommand;
	VisitCommandToVisit visitCommandToVisit;
	PetCommandToPet petCommandToPet;

	/**
	 * @param repository
	 * @param visitToVisitCommand
	 * @param visitCommandToVisit
	 */
	public VisitSDJpaService(R repository, VisitToVisitCommand visitToVisitCommand,
			VisitCommandToVisit visitCommandToVisit, PetCommandToPet petCommandToPet) {
		super(repository);
		this.visitToVisitCommand = visitToVisitCommand;
		this.visitCommandToVisit = visitCommandToVisit;
		this.petCommandToPet = petCommandToPet;
	}

	@Override
	public VisitCommand saveVisitCommandAsEntity(VisitCommand visitCommand) {
		Visit visit = toEntity(visitCommand);
		if (visit != null) {
			visit.setPet(petCommandToPet.convert(visitCommand.getPetCommand()));
			return toCommand(repository.save(visit));
		}	
		return null;
	}

	private VisitCommand toCommand(Visit visit) {
		return visitToVisitCommand.convert(visit);
	}

	private Visit toEntity(VisitCommand visitCommand) {
		return visitCommandToVisit.convert(visitCommand);
	}

}
