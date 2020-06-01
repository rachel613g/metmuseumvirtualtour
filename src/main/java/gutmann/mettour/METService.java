package gutmann.mettour;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface METService
{
    @GET("departments")
    Call<METDepartmentsList> getListOfDepartments();

    @GET("objects?departmentIds=")
    Call<METObjectIds> getObjectIdsInDepartment(@Query("departmentId")int departmentId);

    @GET("objects/{objectId}")
    Call<METObjectData> getMetaData(@Path("objectId") int objectId);
}