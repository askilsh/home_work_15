package petshop;

import clients.petstore.Answer;
import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonTest404 {
    private static PetStore petStore;
    private static Answer notAPet;
    private static Pet myPet;

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetstore();
        notAPet = new Answer().getAnswer();
        notAPet.setCode(1);
        notAPet.setType("error");
        notAPet.setMessage("Pet not found");

        myPet = new Pet();
        myPet.setId(168);
    }

    @Test
    public void testGET404() throws IOException {
        petStore.delPetById(myPet.getId()).execute();
        Assertions.assertEquals(404, petStore.getPetById404(myPet.getId()).execute().code());
        Assertions.assertEquals(notAPet, petStore.getPetById404(myPet.getId()).execute().body());
    }

    @Test
    public void testDELETE404() throws IOException {
        petStore.createPet(myPet).execute();
        petStore.delPetById(myPet.getId()).execute();
        Assertions.assertEquals(404, petStore.delPetById(myPet.getId()).execute().code());
        Assertions.assertNull(petStore.delPetById(myPet.getId()).execute().body());
    }

    @Test
    public void testPUT404() throws IOException, NullPointerException {
        petStore.delPetById(myPet.getId()).execute();
        Assertions.assertEquals(404, petStore.putPet(myPet).execute().code());
        Assertions.assertNotEquals(myPet, petStore.putPet(myPet).execute().body());
        Assertions.assertEquals(notAPet, petStore.putPet(myPet).execute().body());
    }
}
