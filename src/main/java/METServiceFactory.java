import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class METServiceFactory
{
    public METService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        METService service = retrofit.create(METService.class);
        return service;
    }
}
