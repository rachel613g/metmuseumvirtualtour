package gutmann.mettour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class METController
{
    DepartmentsCallback departmentsCallback;
    ObjectIdsCallback objectIdsCallback;
    ObjectDataCallback objectDataCallback;
    private METService service;
    private METObjectImageView objectImageView;
    private JLabel objectImageLabel;
    private JLabel objectIdLabel;
    private JLabel objectCultureLabel;
    private JLabel objectTitleLabel;
    private JLabel objectArtistLabel;
    private JLabel objectDateLabel;

    private int counter = 0;
    private ArrayList<Integer> objectIdArrayList;
    private METObjectData metObjectData;

    JComboBox<METDepartments.Department> departmentComboBox;

    public METController(METService service, METObjectImageView objectImageView,
                         JComboBox<METDepartments.Department> departmentComboBox,
                         JLabel objectImageLabel, JLabel objectIdLabel,
                         JLabel objectTitleLabel,JLabel objectCultureLabel,
                         JLabel objectArtistLabel, JLabel objectDateLabel)
    {
        this.service = service;
        this.objectImageView = objectImageView;
        this.departmentComboBox = departmentComboBox;
        this.objectImageLabel = objectImageLabel;
        this.objectIdLabel = objectIdLabel;
        this.objectTitleLabel = objectTitleLabel;
        this.objectCultureLabel = objectCultureLabel;
        this.objectArtistLabel = objectArtistLabel;
        this.objectDateLabel = objectDateLabel;

        departmentsCallback = new DepartmentsCallback();
        objectIdsCallback = new ObjectIdsCallback();
        objectDataCallback = new ObjectDataCallback();
    }

    public JComboBox<METDepartments.Department> getDepartmentComboBox()
    {
        return departmentComboBox;
    }

    public ArrayList<Integer> getObjectIdArrayList()
    {
        return objectIdArrayList;
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
            List<METDepartments.Department> departmentList = response.body().departments;
            populateJComboBox(departmentList);
        }

        private void populateJComboBox(List<METDepartments.Department> departmentList)
        {
            for (METDepartments.Department displayDepartment : departmentList)
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
            objectIdArrayList = response.body().objectIDs;
            //call the first object
            objectDataCallback.requestData(objectIdArrayList.get(0));
        }

        @Override
        public void onFailure(Call<METObjectIds> call, Throwable t)
        {
            t.printStackTrace();
        }
    }

    class ObjectDataCallback implements Callback<METObjectData>
    {

        private int objectId;

        public void requestData(int objectId)
        {
            this.objectId = objectId;
            service.getMetaData(this.objectId).enqueue(this);
        }

        @Override
        public void onResponse(Call<METObjectData> call, Response<METObjectData> response)
        {
            metObjectData = response.body();
            displayObjectData();
            displayObjectImage();
        }

        private void displayObjectImage()
        {
            //
            try
            {
                //set imageView
                objectImageView.setImage(metObjectData.getPrimaryImage());
                //set imageLabel
                objectImageLabel.setIcon(objectImageView.getImageIcon());
            } catch (NoImageException | MalformedURLException e)
            {
                objectImageLabel.setText(e.getMessage());
            }
        }

        private void displayObjectData()
        {
            //set other data
            objectIdLabel.setText("Object Id: " + Integer.toString(objectId));
            objectTitleLabel.setText(metObjectData.getTitle());
            objectCultureLabel.setText("Culture: " + metObjectData.getCulture());
            objectArtistLabel.setText("Artist: " + metObjectData.getArtistDisplayName());
            objectDateLabel.setText("Historical Data: "+ metObjectData.getObjectDate());
        }

        @Override
        public void onFailure(Call<METObjectData> call, Throwable t)
        {
            t.printStackTrace();
        }
    }
}
