package gutmann.mettour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class METController
{
    METService service;
    METObjectDataView view;

    public METController(METService service, METObjectDataView view)
    {
        this.service = service;
        this.view = view;
        //instantiate the first controller
        METControllerDepartmentList controllerDepartmentList = new METControllerDepartmentList();
    }

    class METControllerDepartmentList implements Callback<METDepartmentsList>
    {

        public void requestData()
        {
            service.getListOfDepartments().enqueue(this);
        }

        @Override
        public void onResponse(Call<METDepartmentsList> call, Response<METDepartmentsList> response)
        {
            /*todo
            save to METFrame components. create them first.
            **/
        }

        @Override
        public void onFailure(Call<METDepartmentsList> call, Throwable t)
        {
            t.printStackTrace();
        }
    }

    class METControllerObjectIds implements Callback<METObjectIds>
    {
        public void requestData(int departmentId)
        {
            service.getObjectIdsInDepartment(departmentId).enqueue(this);
        }
        @Override
        public void onResponse(Call<METObjectIds> call, Response<METObjectIds> response)
        {

        }

        @Override
        public void onFailure(Call<METObjectIds> call, Throwable t)
        {

        }
    }

}
