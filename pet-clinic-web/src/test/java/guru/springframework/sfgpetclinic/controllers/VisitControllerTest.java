package guru.springframework.sfgpetclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

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
import guru.springframework.sfgpetclinic.commands.VisitCommand;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.interfaces.PetService;
import guru.springframework.sfgpetclinic.services.interfaces.VisitService;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

	private static final String CREATE_OR_UPDATE_VISIT_FORM = "pet/visit/createOrUpdateVisitForm";
	private static final String REDIRECT_OWNERS_1 = "redirect:/owner/{ownerId}/show";
	private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

	@Mock
	PetService petService;

	@Mock
	VisitService visitService;

	@InjectMocks
	VisitController visitController;

	private MockMvc mockMvc;
	private OwnerCommand ownerCommand;
	private PetType petType;
	private PetCommand petCommand;

	@BeforeEach
	void setUp() {
		Long petId = 1L;

		ownerCommand = OwnerCommand.builder().build();
		petType = PetType.builder().build();
		petCommand = PetCommand.builder().id(petId).birthDate(LocalDate.of(2018, 11, 13)).ownerCommand(ownerCommand)
				.petType(petType).build();

		when(petService.findCommandById(anyLong())).thenReturn(petCommand);

		mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
	}

	@Test
	void initNewVisitForm() throws Exception {
		when(petService.findCommandById(anyLong())).thenReturn(petCommand);

		mockMvc.perform(get("/owner/1/pet/1/visit/new")).andExpect(status().isOk())
				.andExpect(view().name(CREATE_OR_UPDATE_VISIT_FORM)).andExpect(model().attributeExists("visitCommand"))
				.andExpect(model().attribute("visitCommand", hasProperty("id", nullValue())))
				.andExpect(model().attribute("visitCommand", hasProperty("petCommand", is(petCommand))))
				.andExpect(model().attribute("visitCommand", hasProperty("date", nullValue())))
				.andExpect(model().attribute("visitCommand", hasProperty("description", nullValue())));
	}

	@Test
	void processNewVisitForm() throws Exception {
		VisitCommand savedVisitCommand = VisitCommand.builder().build();
		when(visitService.saveVisitCommandAsEntity(any(VisitCommand.class))).thenReturn(savedVisitCommand);

		mockMvc.perform(post("/owner/1/pet/1/visit/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("date", "2018-11-11").param("description", YET_ANOTHER_VISIT_DESCRIPTION))
				.andExpect(status().is3xxRedirection()).andExpect(view().name(REDIRECT_OWNERS_1))
				.andExpect(model().attributeExists("visitCommand"))
				.andExpect(model().attribute("visitCommand", hasProperty("id", nullValue())))
				.andExpect(model().attribute("visitCommand", hasProperty("petCommand", is(petCommand))))
				.andExpect(model().attribute("visitCommand", hasProperty("date", is(LocalDate.of(2018, 11, 11)))))
				.andExpect(model().attribute("visitCommand",
						hasProperty("description", is(YET_ANOTHER_VISIT_DESCRIPTION))));
	}
}