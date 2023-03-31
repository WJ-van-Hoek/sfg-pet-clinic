/**
 * 
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.commands.OwnerCommand;
import guru.springframework.sfgpetclinic.mappers.OwnerCommandToOwner;
import guru.springframework.sfgpetclinic.mappers.OwnerToOwnerCommand;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;

/**
 * @author hoek0024 on 31 mrt. 2023
 *
 */
@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	@Mock
	OwnerToOwnerCommand ownerToOwnerCommand;
	@Mock
	OwnerCommandToOwner ownerCommandToOwner;
	@Mock
	OwnerRepository<Owner> ownerRepository;

	@InjectMocks
	OwnerSDJpaService<Owner, OwnerRepository<Owner>> ownerSDJpaService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testFindCommandById() {
		Owner owner = new Owner();
		owner.setId(1l);
		OwnerCommand ownerCommand = new OwnerCommand();
		ownerCommand.setId(1l);

		when(ownerRepository.findById(any(Long.class))).thenReturn(Optional.of(owner));
		when(ownerToOwnerCommand.convert(any(Owner.class))).thenReturn(ownerCommand);

		assertEquals(ownerCommand, ownerSDJpaService.findCommandById(1l));
		verify(ownerRepository, times(1)).findById(any(Long.class));
		verify(ownerToOwnerCommand, times(1)).convert(any(Owner.class));
	}

	@Test
	void testSaveOwnerCommandAsEntity() {
		Owner owner = new Owner();
		owner.setId(1l);
		
		OwnerCommand ownerCommand = new OwnerCommand();
		ownerCommand.setId(1l);
		
		when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
		when(ownerCommandToOwner.convert(any(OwnerCommand.class))).thenReturn(owner);
		when(ownerToOwnerCommand.convert(any(Owner.class))).thenReturn(ownerCommand);
		
		assertEquals(ownerCommand, ownerSDJpaService.saveOwnerCommandAsEntity(ownerCommand));
		verify(ownerRepository, times(1)).save(any(Owner.class));
		verify(ownerCommandToOwner, times(1)).convert(any(OwnerCommand.class));
		verify(ownerToOwnerCommand, times(1)).convert(any(Owner.class));
	}
}
