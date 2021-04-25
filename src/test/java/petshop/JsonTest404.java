package petshop;

import clients.petstore.Answer;
import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

public class JsonTest404 {
    private static PetStore petStore;
    private static Answer notAPet;
    private static Pet myPet;
    private static Response<Answer> respGet;
    private static Response<Pet> respPut;
    private static Response<Answer> respDel;
    private static Response<Pet> respPost;

    private static void setRespGet() throws IOException {
        respGet = petStore.getPetById404(myPet.getId()).execute();
    }

    private static void setRespPut() throws IOException {
        respPut = petStore.putPet404(myPet).execute();
    }

    private static void setRespDel() throws IOException {
        respDel = petStore.delPetById(myPet.getId()).execute();
    }

    private static void setRespPost() throws IOException {
        respPost = petStore.createPet(myPet).execute();
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        petStore = new PetStoreService().getPetstore();
        notAPet = new Answer().getAnswer();
        notAPet.setCode(1);
        notAPet.setType("error");
        notAPet.setMessage("Pet not found");

        myPet = new Pet();
        myPet.setId(168);

        setRespDel();
        setRespGet();
        setRespPut();
        setRespDel();
    }

    @Test
    public void testGET404() throws IOException {
        setRespDel();
        Assertions.assertEquals(404, respGet.code());
        Assertions.assertNotNull(respGet.errorBody());
    }

    @Test
    public void testDELETE404() throws IOException {
        setRespDel();
        Assertions.assertEquals(404, respDel.code());
        Assertions.assertNull(respDel.body());
    }

    @Test
    public void testPUT404() throws IOException, NullPointerException {
        setRespDel();
        Assertions.assertEquals(404, respPut.code());
        Assertions.assertNotNull(respPut.errorBody());
    }
}
