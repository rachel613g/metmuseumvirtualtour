package gutmann.mettour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.net.MalformedURLException;

public class METController
{
    METService service;
    METObjectImageView objectImageView;
    DepartmentsCallback departmentsCallback;
    ObjectIdsCallback objectIdsCallback;
    ObjectDataCallback objectDataCallback;

    public METController(METService service, METObjectImageView objectImageView)
    {
        this.service = service;
        this.objectImageView = objectImageView;
        departmentsCallback = new DepartmentsCallback();
        objectIdsCallback = new ObjectIdsCallback();
        objectDataCallback = new ObjectDataCallback();
    }

    class DepartmentsCallback implements Callback<METDepartments>
    {
        JComboBox<String> displayNamesComboBox;
        public void requestData(JComboBox<String> displayNamesComboBox)
        {
            service.getListOfDepartments().enqueue(this);
            this.displayNamesComboBox = displayNamesComboBox;
        }

        @Override
        public void onResponse(Call<METDepartments> call, Response<METDepartments> response)
        {
            METDepartments departmentList = response.body();
            displayNamesComboBox = new JComboBox<>(departmentList.getArrayOfDisplayNames());
        }

        @Override
        public void onFailure(Call<METDepartments> call, Throwable t)
        {
            t.printStackTrace();
        }
    }

    class ObjectIdsCallback implements Callback<METObjectIds>
    {
        public void requestData(int departmentId)
        {
            service.getObjectIdsInDepartment(departmentId).enqueue(this);
        }

        @Override
        public void onResponse(Call<METObjectIds> call, Response<METObjectIds> response)
        {
            METObjectIds metObjectIds = response.body();

            objectDataCallback.requestData(metObjectIds.objectIDs.get(0));
        }

        @Override
        public void onFailure(Call<METObjectIds> call, Throwable t)
        {
            t.printStackTrace();
        }
    }

    class ObjectDataCallback implements Callback<METObjectData>
    {


        public void requestData(int objectId)
        {
            service.getMetaData(objectId).enqueue(this);
        }

        @Override
        public void onResponse(Call<METObjectData> call, Response<METObjectData> response)
        {
            METObjectData metObjectData = response.body();
            /*
            todo
            set frame variables an necessary.
             */
            try
            {
                objectImageView.setImage(metObjectData.primaryImage);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<METObjectData> call, Throwable t)
        {
            t.printStackTrace();
        }
    }
}
