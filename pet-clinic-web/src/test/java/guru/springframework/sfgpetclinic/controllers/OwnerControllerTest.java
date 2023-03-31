/**
 * 
 */
package guru.springframework.sfgpetclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.interfaces.OwnerService;

/**
 * @author Hoek0024 on 17 mrt. 2023
 *
 */
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController ownerController;

	Set<Owner> owners;

	MockMvc mockMvc;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1l).build());
		owners.add(Owner.builder().id(2l).build());

		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}

	@Test
	void testProcessFindFormReturnOne() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1l).build()));

		mockMvc.perform(get("/owners")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owner/1/show"));
	}

	@Test
	void testProcessFindFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners.stream().collect(Collectors.toList()));

		mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/ownersList"))
				.andExpect(model().attribute("selections", hasSize(2)));
	}

	/**
	 * Test method for
	 * {@link guru.springframework.sfgpetclinic.controllers.OwnerController#findOwners()}.
	 */
	@Test
	void testFindOwners() throws Exception {
		mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(view().name("owners/findOwners"))
				.andExpect(model().attributeExists("owner"));
		verifyZeroInteractions(ownerService);
	}

	@Test
	void testShowOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());
		mockMvc.perform(get("/owner/1/show")).andExpect(status().isOk()).andExpect(view().name("owner/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1l))));
	}
	
	@Test
	void testNewOwner() throws Exception {
		mockMvc.perform(get("/owner/1/new")).andExpect(status().isOk()).andExpect(view().name("owner/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void testUpdateOwner() throws Exception {
		OwnerCommand ownerCommand = new OwnerCommand();
		ownerCommand.setId(1l);
		
		when(ownerService.findCommandById(anyLong())).thenReturn(ownerCommand);
		mockMvc.perform(get("/owner/1/update")).andExpect(status().isOk()).andExpect(view().name("owner/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner")).andExpect(model().attribute("owner.id", 1));
	}
}
