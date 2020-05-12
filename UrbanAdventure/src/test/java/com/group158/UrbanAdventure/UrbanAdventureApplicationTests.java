package com.group158.UrbanAdventure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrbanAdventureApplicationTests {

	@InjectMocks
	private AdventureController adventureController;

	@Mock
	private AdventureRepository adventureRepository;

    public void init() {
        MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void TestGetAllStoriesController() {
		List<Event> events = new ArrayList<Event>();
		events.add(new EventPrompt(1, 2, "Gå till den närmsta gatlyktan. Där finns en galaktisk krypteringsnyckel som gör att Hekate inte kan avlyssna oss.", "Mata in koden på lyktstolpen", 
			"G8:118", 1));
		events.add(new EventMessage(2, 3, "Bra jobbat. Ni är vid templet. I er värld kan det vara någon annan helig plats av något slag. Det är svårt att säga. I min värld står det ett Ishirs tempel. För att komma vidare måste ni räkna ut hur många trappsteg trappan upp till templet har. När ni vet antalet trappsteg, gå närmare templet..."));
		List<AdventurePart> parts = new ArrayList<AdventurePart>();
		parts.add(new AdventurePart(1, new Location(30,59.2936735,17.9921344), events));
		List<Adventure> expected = new ArrayList<Adventure>();
		expected.add(new Adventure("Battle of Teleplan", 2, parts, "horror"));

		when(adventureRepository.findAll()).thenReturn(expected);

		List<Adventure> result = adventureController.getAllStories().getBody();

		verify(adventureRepository).findAll();

		assertEquals(expected, result);
	}

	// @Test
	// void testGetByAdventureTitle(){
	// 	List<Event> events = new ArrayList<Event>();
	// 	events.add(new EventPrompt(1, 2, "Gå till den närmsta gatlyktan. Där finns en galaktisk krypteringsnyckel som gör att Hekate inte kan avlyssna oss.", "Mata in koden på lyktstolpen", 
	// 		"G8:118", 1));
	// 	events.add(new EventMessage(2, 3, "Bra jobbat. Ni är vid templet. I er värld kan det vara någon annan helig plats av något slag. Det är svårt att säga. I min värld står det ett Ishirs tempel. För att komma vidare måste ni räkna ut hur många trappsteg trappan upp till templet har. När ni vet antalet trappsteg, gå närmare templet..."));
	// 	List<AdventurePart> parts = new ArrayList<AdventurePart>();
	// 	parts.add(new AdventurePart(1, new Location(30,59.2936735,17.9921344), events));
	// 	Adventure adventure = new Adventure("Battle of Teleplan", 2, parts, "horror");
	// 	Optional<Adventure> expected = Optional.of(adventure);
		
	// 	when(adventureRepository.findByAdventureTitle("Battle of Teleplan")).thenReturn(expected);

	// 	Adventure result = adventureController.getByAdventureTitle("Battle of Teleplan").getBody();

	// 	verify(adventureRepository).findByAdventureTitle("Battle of Teleplan");

	// 	assertEquals(expected.get(), result);
	// }
}