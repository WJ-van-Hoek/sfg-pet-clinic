/**
 * 
 */
package guru.springframework.sfgpetclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;


/**
 * @author Hoek0024 on 15 feb. 2023
 *
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

	private final OwnerService ownerService;

	/**
	 * @param ownerService
	 */
	@Autowired
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@GetMapping({ "", "/", "/index", "/index.html" })
	public String listOwners(Model model) {
		
		model.addAttribute("owners", ownerService.findAll());
		
		return "owners/index";
	}

	@GetMapping("/find")
	public String findOwners() {
		return "notimplemented";
	}
	
    @GetMapping("/{ownerId}/show")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}