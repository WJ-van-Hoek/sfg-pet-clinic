package guru.springframework.sfgpetclinic.services.map_implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.interfaces.PetTypeService;

class PetMapServiceTest {
	@Mock
	private PetTypeService petTypeService;
	
	@InjectMocks
    private PetMapService petMapService;

    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	doNothing().when(petTypeService).nullCheck(notNull());
    	doThrow(new RuntimeException("Entity can not be null")).when(petTypeService).nullCheck(null);

    	PetType petType = PetType.builder().id(1l).build();
    	Pet pet = Pet.builder().id(petId).petType(petType).build();
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();
        assertEquals(1, petSet.size());
    }

    @Test
    void findByIdExistingId() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdNotExistingId() {
        Pet pet = petMapService.findById(5L);
        assertNull(pet);
    }

    @Test
    void findByIdNullId() {
        Pet pet = petMapService.findById(null);
        assertNull(pet);
    }

    @Test
	void saveExistingIdExistingPetType() {
        Long typeId = 2L;
        PetType cat = PetType.builder().id(typeId).name("cat").build();
    	
    	Long petId = 3L;
        Pet pet2 = Pet.builder().id(petId).petType(cat).build();
        
        Pet savedPet = petMapService.save(pet2);
        assertEquals(petId, savedPet.getId());
        assertEquals(cat, savedPet.getPetType());
        verify(petTypeService, times(1)).nullCheck(cat);
    }
    
    @Test
    void saveNoIdExistingPetType() {
        Long typeId = 2L;
        PetType cat = PetType.builder().id(typeId).name("cat").build();
    	
        Pet pet2 = Pet.builder().petType(cat).build();

        Pet savedPet = petMapService.save(pet2);
        
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
        verify(petTypeService, times(1)).nullCheck(cat);
    }
    
    @Test
    void saveDuplicateIdExistingPetType() {
        Long typeId = 2L;
        PetType cat = PetType.builder().id(typeId).name("cat").build();
    	
    	Long petId = 1L;
        Pet pet2 = Pet.builder().id(petId).petType(cat).build();

        Pet savedPet = petMapService.save(pet2);
        assertEquals(petId, savedPet.getId());
        assertEquals(1, petMapService.findAll().size());
        verify(petTypeService, times(1)).nullCheck(cat);
    }

    @Test
	void saveExistingIdNoExistingPetType() {
    	Long petId = 3L;
        Pet pet2 = Pet.builder().id(petId).build();
        
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> petMapService.save(pet2));
        assertTrue(thrown.getMessage().contentEquals("Entity can not be null"));
    }

    @Test
    void deletePet() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();
        petMapService.delete(pet);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {
        Pet pet = Pet.builder().build();
        petMapService.delete(pet);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteNull() {
        petMapService.delete(null);
        assertEquals(1, petMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {
        petMapService.deleteById(petId);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {
        petMapService.deleteById(5L);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {
        petMapService.deleteById(null);
        assertEquals(1, petMapService.findAll().size());
    }
}