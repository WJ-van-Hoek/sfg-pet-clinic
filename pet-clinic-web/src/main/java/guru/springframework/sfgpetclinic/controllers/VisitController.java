package guru.springframework.sfgpetclinic.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.sfgpetclinic.commands.PetCommand;
import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;
import guru.springframework.sfgpetclinic.services.interfaces.VisitService;

/**
 * Created by jt on 2018-09-27.
 */
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("visitCommand")
    public VisitCommand loadPetWithVisit(@PathVariable Long petId, ModelMap model) {
        PetCommand petCommand = petService.findCommandById(petId);
        model.put("petCommand", petCommand);
        VisitCommand visitCommand = new VisitCommand();
        petCommand.addVisitCommand(visitCommand);
        return visitCommand;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owner/*/pet/{petId}/visit/new")
    public String initNewVisitForm(@PathVariable Long petId, ModelMap model) {
        return "pet/visit/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owner/{ownerId}/pet/{petId}/visit/new")
    public String processNewVisitForm(@Valid VisitCommand visitCommand, BindingResult result) {
        if (result.hasErrors()) {
            return "pet/visit/createOrUpdateVisitForm";
        } else {
            visitService.saveVisitCommandAsEntity(visitCommand);
            return "redirect:/owner/{ownerId}/show";
        }
    }
}