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
            METObjectIds metObjectIds = response.body();
            METControllerObjectData metControllerObjectData = new METControllerObjectData(metObjectIds.objectIDs.get(0));
        }

        @Override
        public void onFailure(Call<METObjectIds> call, Throwable t)
        {
            t.printStackTrace();
        }
    }

    class METControllerObjectData implements Callback<METObjectData>
    {
        //this constructor just calls the requestData method.
        // It wasn't necessary for me to make an arg constructor.
        //I could of just instantiated METControllerObjectData and then
        //called the requestData method on the next line
        public METControllerObjectData(int objectId)
        {
            requestData(objectId);
        }

        public void requestData(int objectId)
        {
            service.getMetaData(objectId).enqueue(this);
        }

        @Override
        public void onResponse(Call<METObjectData> call, Response<METObjectData> response)
        {
            METObjectData metObjectData = response.body();
            /*
            possible todo
            set frame variables an necessary.
             */
        }

        @Override
        public void onFailure(Call<METObjectData> call, Throwable t)
        {
            t.printStackTrace();
        }
    }
}
