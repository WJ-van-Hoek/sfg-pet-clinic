/**
 * 
 */
package guru.springframework.sfgpetclinic.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
@ExtendWith(MockitoExtension.class)
class PetControllerTest {

	@Mock
	PetService petService;

	@Mock
	OwnerService ownerService;

	@Mock
	PetTypeService petTypeService;

	@InjectMocks
	PetController petController;

	MockMvc mockMvc;

	OwnerCommand ownerCommand;
	PetCommand petCommand;
	Set<PetType> petTypes;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		petTypes = new HashSet<>();
		PetType petType1 = new PetType();
		petType1.setId(3l);
		petType1.setName("Dog");
		PetType petType2 = new PetType();
		petType2.setId(4l);
		petType2.setName("Cat");
		petTypes.add(petType1);
		petTypes.add(petType2);

		petCommand = new PetCommand();
		petCommand.setId(2l);
		petCommand.setPetType(petType1);

		ownerCommand = new OwnerCommand();
		ownerCommand.setId(1l);
		ownerCommand.addPetCommand(petCommand);

		mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
	}

	@Test
	void testNewPet() throws Exception {
		when(ownerService.findCommandById(anyLong())).thenReturn(ownerCommand);
		when(petTypeService.findAllPetTypes()).thenReturn(petTypes);

		mockMvc.perform(get("/owner/1/pet/new")).andExpect(status().isOk())
				.andExpect(view().name("pet/createOrUpdatePetForm")).andExpect(model().attributeExists("petCommand"))
				.andExpect(model().attributeExists("ownerCommand"))
				.andExpect(model().attribute("ownerCommand", equalTo(ownerCommand)));
	}

	@Test
	void testPostNewPet() throws Exception {
		when(ownerService.findCommandById(anyLong())).thenReturn(ownerCommand);

		mockMvc.perform(post("/owner/1/pet/new")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owner/" + ownerCommand.getId() + "/show"))
				.andExpect(model().attributeExists("petCommand")).andExpect(model().attributeExists("ownerCommand"));
	}

	@Test
	void testUpdateOwner() throws Exception {
		when(ownerService.findCommandById(anyLong())).thenReturn(ownerCommand);
		when(petService.findCommandById(anyLong())).thenReturn(petCommand);

		mockMvc.perform(get("/owner/1/pet/2/update")).andExpect(status().isOk())
				.andExpect(view().name("pet/createOrUpdatePetForm")).andExpect(model().attributeExists("petCommand"))
				.andExpect(model().attributeExists("ownerCommand"))
				.andExpect(model().attribute("ownerCommand", equalTo(ownerCommand)))
				.andExpect(model().attribute("petCommand", equalTo(petCommand)));
	}

	@Test
	void testPostUpdateOwner() throws Exception {
		when(ownerService.findCommandById(anyLong())).thenReturn(ownerCommand);

		mockMvc.perform(post("/owner/1/pet/2/update").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr("ownerCommand", ownerCommand).sessionAttr("petCommand", petCommand))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owner/" + ownerCommand.getId() + "/show"))
				.andExpect(model().attributeExists("petCommand")).andExpect(model().attributeExists("ownerCommand"));
	}
}
