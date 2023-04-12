/**
 * 
 */
package guru.springframework.sfgpetclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;
import guru.springframework.sfgpetclinic.services.interfaces.PetTypeService;

/**
 * @author Hoek0024 on 3 apr. 2023
 *
 */
@Controller
public class PetController {

	// mappings
	private static final String PET_NEW = "/owner/{ownerId}/pet/new";
	private static final String PET_UPDATE = "/owner/{ownerId}/pet/{petId}/update";

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
		this.petTypeService = petTypeService;
	}

	@ModelAttribute("petTypes")
	public Collection<PetType> findAllPetTypes() {
		return petTypeService.findAllPetTypes();
	}

	@ModelAttribute("petCommands")
	public Collection<PetCommand> findAllPetCommandsOfOwner(@PathVariable Long ownerId) {
		return petService.findAllPetCommandsOfOwner(ownerId);
	}

	@ModelAttribute("ownerCommand")
	public OwnerCommand findOwnerCommand(@PathVariable Long ownerId) {
		return ownerService.findCommandById(ownerId);
	}

	@InitBinder("ownerCommand")
	public void initOwnerCommandBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}

	@GetMapping(PET_NEW)
	public String newPet(OwnerCommand ownerCommand, Model model) {
		return newModelWithNewPetCommand(ownerCommand, model);
	}

	@GetMapping(PET_UPDATE)
	public String updatePet(OwnerCommand ownerCommand, @PathVariable Long petId, Model model) {
		model.addAttribute("petCommand", petService.findCommandById(petId));
		return PET_FORM;
	}

	@PostMapping(PET_NEW)
	public String processNewPet(OwnerCommand ownerCommand, @Valid PetCommand petCommand, BindingResult result,
			ModelMap model) {
		if (StringUtils.hasLength(petCommand.getName()) && petCommand.isNew()
				&& ownerService.findPetCommandByName(ownerCommand, petCommand.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		if (result.hasErrors()) {
			model.put("petCommand", petCommand);
			return PET_FORM;
		} else {
			ownerCommand.addPetCommand(petCommand);
			ownerService.saveOwnerCommandAsEntity(ownerCommand);
			return "redirect:/owner/" + ownerCommand.getId() + "/show";
		}
	}

	@PostMapping(PET_UPDATE)
	public String processUpdatePet(OwnerCommand ownerCommand, @Valid PetCommand petCommand, BindingResult result,
			ModelMap model) throws Exception {

		petCommand.setOwnerCommand(ownerCommand);
		if (result.hasErrors()) {
			model.put("petCommand", petCommand);
			return PET_FORM;
		} else {
			ownerService.updatePetCommand(petCommand);
			ownerService.saveOwnerCommandAsEntity(ownerCommand);
			return "redirect:/owner/" + ownerCommand.getId() + "/show";
		}
	}

	private String newModelWithNewPetCommand(OwnerCommand ownerCommand, Model model) {
		PetCommand petCommand = new PetCommand();
		petCommand.setOwnerCommand(ownerCommand);
		ownerCommand.addPetCommand(petCommand);
		model.addAttribute("petCommand", petCommand);
		return PET_FORM;
	}

}
