package clients.petstore;

import models.pet.Pet;
import retrofit2.Call;
import retrofit2.http.*;

public interface PetStore {
    @POST("/v2/pet")
    Call<Pet> createPet(@Body Pet pet);

    @GET("/v2/pet/{id}")
    Call<Pet> getPetById(@Path("id") int id);

    @PUT("/v2/pet/")
    Call<Pet> putPet(@Body Pet pet);

    @DELETE("/v2/pet/{id}")
    Call<Answer> delPetById(@Path("id") int id);

    @GET("/v2/pet/{id}")
    Call<Answer> getPetById404(@Path("id") int id);

    @PUT("/v2/pet/")
    Call<Pet> putPet404(@Body Pet pet);

}

