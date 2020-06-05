package gutmann.mettour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;

public class METController
{
    private METService service;
    private METObjectImageView objectImageView;
    DepartmentsCallback departmentsCallback;
    ObjectIdsCallback objectIdsCallback;
    ObjectDataCallback objectDataCallback;

    private METDepartments departments;
    private METObjectIds metObjectIds;
    private METObjectData metObjectData;

    JComboBox<String> displayNamesComboBox;

    public METController(METService service, METObjectImageView objectImageView)
    {
        this.service = service;
        this.objectImageView = objectImageView;
        departmentsCallback = new DepartmentsCallback();
        objectIdsCallback = new ObjectIdsCallback();
        objectDataCallback = new ObjectDataCallback();
        displayNamesComboBox = new JComboBox<>();
    }

    public JComboBox<String> getDisplayNamesComboBox()
    {
        return displayNamesComboBox;
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
            departments = response.body();
            populateJComboBox();
            setJComboBoxSettings();
        }

        private void setJComboBoxSettings()
        {
            displayNamesComboBox.setSelectedIndex(0);
            displayNamesComboBox.setPreferredSize(new Dimension(200,200));
        }

        private void populateJComboBox()
        {
            for(String displayName: departments.getArrayOfDisplayNames())
            {
                displayNamesComboBox.addItem(displayName);

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
