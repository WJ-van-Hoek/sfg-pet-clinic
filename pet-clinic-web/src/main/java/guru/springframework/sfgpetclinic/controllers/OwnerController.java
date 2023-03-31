/**
 * 
 */
package guru.springframework.sfgpetclinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;

/**
 * @author Hoek0024 on 15 feb. 2023
 *
 */
@Controller
public class OwnerController {

	// mappings
	private static final String OWNERS = "/owners";
	private static final String OWNERS_FIND = "/owners/find";
	private static final String OWNER_OWNER_ID_SHOW = "/owner/{ownerId}/show";
	private static final String OWNER_NEW = "/owner/new";
	private static final String OWNER_UPDATE = "/owner/{ownerId}/update";

	// directions
	private static final String OWNER_DETAILS = "owner/ownerDetails";
	private static final String OWNERS_LIST = "owners/ownersList";
	private static final String FIND_OWNERS = "owners/findOwners";
	private static final String OWNER_FORM = "owner/createOrUpdateOwnerForm";
	private final OwnerService ownerService;

	/**
	 * @param ownerService
	 */
	@Autowired
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	// Mark fields as disallowed for example to avoid unwanted modifications
	// by malicious users when binding HTTP request parameters.
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(OWNERS)
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return FIND_OWNERS;
		} else if (results.size() == 1) {
			// 1 owner found
			owner = results.get(0);
			return "redirect:/owner/" + owner.getId() + "/show";
		} else {
			// multiple owners found
			model.addAttribute("selections", results);
			return OWNERS_LIST;
		}
	}

	@GetMapping(OWNERS_FIND)
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return FIND_OWNERS;
	}

	@GetMapping(OWNER_OWNER_ID_SHOW)
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView(OWNER_DETAILS);
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}
	
	@GetMapping(OWNER_NEW)
	public ModelAndView newOwner() {
		ModelAndView mav = new ModelAndView(OWNER_FORM);
		mav.addObject(Owner.builder().build());
		return mav;
	}
	
	@GetMapping(OWNER_UPDATE)
	public ModelAndView updateOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView(OWNER_FORM);
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}

}