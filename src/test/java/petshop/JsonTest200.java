package petshop;

import clients.petstore.Answer;
import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Category;
import models.pet.Pet;
import models.pet.TagsItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class JsonTest200 {
    private static PetStore petStore;

    public static Stream<Arguments> param() {
        Pet myPet1 = new Pet();
        myPet1.setId(2147483647);
        myPet1.setName("doggy-froggy 342fdfgvdsdds  sdddssdcdassaascxzxxzzz              fpojppkopo  1p p1k mp1 m1 pk1 mkp1m p 1mp1 mkp1m pk1 ");
        Category category1 = new Category();
        category1.setName("PetForBet doggy-froggy342fdfgvdsdds  sdddssdcdassaascxzxxzzz              fpojppkopo  1p p1k mp1 m1 pk1 mkp1m p 1mp1 mkp1m pk1 ");
        category1.setId(2147483647);
        myPet1.setCategory(category1);
        myPet1.setStatus("avalible doggy-froggy342fdfgvdsdds  sdddssdcdassaascxzxxzzz              fpojppkopo  1p p1k mp1 m1 pk1 mkp1m p 1mp1 mkp1m pk1 ");
        myPet1.setPhotoUrls(List.of("Здесь могло быть ваше фото doggy-froggy342fdfgvdsdds  sdddssdcdassaascxzxxzzz              fpojppkopo  1p p1k mp1 m1 pk1 mkp1m p 1mp1 mkp1m pk1 "));
        TagsItem tagsItem1 = new TagsItem();
        tagsItem1.setId(2147483647);
        tagsItem1.setName("abrakatabra doggy-froggy342fdfgvdsdds  sdddssdcdassaascxzxxzzz              fpojppkopo  1p p1k mp1 m1 pk1 mkp1m p 1mp1 mkp1m pk1 ");
        myPet1.setTags(List.of(tagsItem1));

        Pet myPet2 = new Pet();
        myPet2.setId(8);
        myPet2.setName("");
        Category category2 = new Category();
        category2.setName("");
        category2.setId(8);
        myPet2.setCategory(category2);
        myPet2.setStatus("");
        myPet2.setPhotoUrls(List.of(""));
        TagsItem tagsItem2 = new TagsItem();
        tagsItem2.setId(8);
        tagsItem2.setName("");
        myPet2.setTags(List.of(tagsItem2));

        Pet myPet3 = new Pet();
        myPet3.setId(168);

        return Stream.of(myPet1, myPet2, myPet3).map(Arguments::of);
    }

    private Response<Pet> getRespGet(final Pet myPet) throws IOException {
        return petStore.getPetById(myPet.getId()).execute();
    }

    private Response<Pet> getRespPost(final Pet myPet) throws IOException {
        return petStore.createPet(myPet).execute();
    }

    private Response<Pet> getRespPut(final Pet myPet) throws IOException {
        return petStore.putPet(myPet).execute();
    }

    private Response<Answer> getRespDel(final Pet myPet) throws IOException {
        return petStore.delPetById(myPet.getId()).execute();
    }

    @BeforeAll
    public static void beforeAll() {
        petStore = new PetStoreService().getPetstore();
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void testPOST200(final Pet myPet) throws IOException {
        Response<Pet> respPost = getRespPost(myPet);
        Assertions.assertEquals(200, respPost.code());
        Assertions.assertEquals(myPet, respPost.body());
        getRespDel(myPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void testGET200(final Pet myPet) throws IOException {
        getRespPost(myPet);
        Response<Pet> respGet = getRespGet(myPet);
        Assertions.assertEquals(200, respGet.code());
        Assertions.assertEquals(myPet, respGet.body());
        getRespDel(myPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void testDELETE200(final Pet myPet) throws IOException {
        Answer notAPet = new Answer().getAnswer();
        notAPet.setCode(200);
        notAPet.setType("unknown");
        notAPet.setMessage(Integer.toString(myPet.getId()));

        getRespPost(myPet);
        Response<Answer> respDel = getRespDel(myPet);
        Assertions.assertEquals(200, respDel.code());
        Assertions.assertEquals(notAPet, respDel.body());
        Assertions.assertEquals(404, getRespGet(myPet).code());
        getRespDel(myPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void testPUT200(final Pet myPet) throws IOException, NullPointerException {
        Pet puffPet = getRespPost(myPet).body();
        puffPet.setStatus("STOLEN");
        Response<Pet> respPut = getRespPut(puffPet);
        Response<Pet> respGet = getRespGet(puffPet);
        Assertions.assertEquals(200, respPut.code());
        Assertions.assertEquals(puffPet, respGet.body());
        Assertions.assertNotEquals(myPet.getStatus(), respGet.body().getStatus());
        getRespDel(puffPet);
    }
}

