package gutmann.mettour;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface METService
{
    @GET("public/collection/v1/departments")
    Call<METDepartments> getListOfDepartments();

    @GET("public/collection/v1/objects?")
    Call<METObjectIds> getObjectIdsInDepartment(@Query("departmentIds")int departmentId);

    @GET("public/collection/v1/objects/{objectId}")
    Call<METObjectData> getMetaData(@Path("objectId") int objectId);
}
