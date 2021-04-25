package clients.petstore;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class PetStoreService {
    private PetStore petstore;

    public PetStore getPetstore() {
        if (petstore == null) {
            Retrofit retrof = new Retrofit.Builder().
                    baseUrl("https://petstore.swagger.io/").
                    addConverterFactory(JacksonConverterFactory.create()).
                    build();
            petstore = retrof.create(PetStore.class);
            return petstore;
        } else {
            return petstore;
        }
    }
}
