package gutmann.mettour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.net.MalformedURLException;
import java.util.List;

public class METController
{
    private METService service;
    private METObjectImageView objectImageView;
    DepartmentsCallback departmentsCallback;
    ObjectIdsCallback objectIdsCallback;
    ObjectDataCallback objectDataCallback;

    private METObjectIds metObjectIds;
    private METObjectData metObjectData;

    JComboBox<METDepartments.Department> departmentComboBox;

    public METController(METService service, METObjectImageView objectImageView,
                         JComboBox<METDepartments.Department> departmentComboBox)
    {
        this.service = service;
        this.objectImageView = objectImageView;
        this.departmentComboBox = departmentComboBox;
        departmentsCallback = new DepartmentsCallback();
        objectIdsCallback = new ObjectIdsCallback();
        objectDataCallback = new ObjectDataCallback();
    }

    public JComboBox<METDepartments.Department> getDepartmentComboBox()
    {
        return departmentComboBox;
    }

    class DepartmentsCallback implements Callback<METDepartments>
    {

        public void requestData()
        {
            service.getListOfDepartments().enqueue(this);
        }

        @Override
        public void onResponse(Call<METDepartments> call, Response<METDepartments> response)
        {
            List<METDepartments.Department> departmentList= response.body().departments;
            populateJComboBox(departmentList);
        }

        private void populateJComboBox(List<METDepartments.Department> departmentList)
        {
            for(METDepartments.Department displayDepartment: departmentList)
            {
                departmentComboBox.addItem(displayDepartment);
            }
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
            metObjectIds = response.body();

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
            metObjectData = response.body();
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
