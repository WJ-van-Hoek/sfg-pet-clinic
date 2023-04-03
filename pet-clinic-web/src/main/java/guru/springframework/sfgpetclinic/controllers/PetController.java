/**
 * 
 */
package guru.springframework.sfgpetclinic.controllers;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetTypeCommand;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;
import guru.springframework.sfgpetclinic.services.interfaces.PetTypeService;

/**
 * @author Hoek0024 on 3 apr. 2023
 *
 */
@Controller
public class PetController {
	
	private static final String PET_FORM = "pet/createOrUpdatePetForm";

	OwnerService ownerService;
	PetService petService;
	PetTypeService petTypeService;

	/**
	 * @param ownerService
	 * @param petService
	 */
	public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.petService = petService;
	}
	
	@ModelAttribute("petTypeCommands")
	public Collection<PetTypeCommand> findAllPetTypeCommands() {
		return petTypeService.findAllPetTypeCommands();
	}

	@ModelAttribute("ownerCommand")
	public OwnerCommand findOwnerCommand(@PathVariable Long ownerId) {
		return ownerService.findCommandById(ownerId);
	}

	@ModelAttribute("ownerCommand")
	public void initOwnerCommandBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}
}
