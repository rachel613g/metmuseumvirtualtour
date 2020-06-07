package gutmann.mettour;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class METController
{
    DepartmentsCallback departmentsCallback;
    ObjectIdsCallback objectIdsCallback;
    ObjectDataCallback objectDataCallback;
    private METFrame frame;
    private METService service;
    private METObjectImageIconView objectImageView;
    private JLabel objectImageLabel;
    private JLabel noImageLabel;
    private JLabel objectIdLabel;
    private JLabel objectCultureLabel;
    private JLabel objectTitleLabel;
    private JLabel objectArtistLabel;
    private JLabel objectDateLabel;

    private int counter;
    private ArrayList<Integer> objectIdArrayList;
    private String objectImageURL;
    private String objectTitleString;
    private String objectCultureString;
    private String objectArtistString;
    private String objectDateString;

    JComboBox<METDepartments.Department> departmentComboBox;

    public METController(METFrame frame, METService service, METObjectImageIconView objectImageView,
                         JComboBox<METDepartments.Department> departmentComboBox,
                         JLabel noImageLabel, JLabel objectImageLabel, JLabel objectIdLabel,
                         JLabel objectTitleLabel, JLabel objectCultureLabel,
                         JLabel objectArtistLabel, JLabel objectDateLabel)
    {
        this.frame = frame;
        this.service = service;
        this.objectImageView = objectImageView;
        this.departmentComboBox = departmentComboBox;
        this.objectImageLabel = objectImageLabel;
        this.noImageLabel = noImageLabel;
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

        protected void populateJComboBox(List<METDepartments.Department> departmentList)
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
            counter = 0;
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
            objectImageURL = response.body().getPrimaryImage();
            objectTitleString = response.body().getTitle();
            objectCultureString = response.body().getCulture();
            objectArtistString = response.body().getArtistDisplayName();
            objectDateString = response.body().getObjectDate();
            displayObjectData();
            displayObjectImage();
            frame.setArrowPanel();
        }

        private void displayObjectImage()
        {
            try
            {
                objectImageLabel.setText("");
                //set imageView
                objectImageView.setImage(objectImageURL, objectImageLabel.getWidth(), objectImageLabel.getHeight());
                //set imageLabel on JFrame
                objectImageLabel.setIcon(objectImageView.getImageIcon());
                objectImageLabel.setPreferredSize(new Dimension(objectImageView.getIconWidth(), objectImageView.getIconHeight()));
            } catch (NoImageException | MalformedURLException e)
            {
                e.getMessage();
                e.printStackTrace();
                noImageLabel.setText("No image to display.");
            }
        }

        private void displayObjectData()
        {
            //set other data
            objectIdLabel.setText("Object Id: " + Integer.toString(objectId));
            objectTitleLabel.setText(objectTitleString);
            objectCultureLabel.setText("Culture: " + objectCultureString);
            objectArtistLabel.setText("Artist: " + objectArtistString);
            objectDateLabel.setText("Historical Data: "+ objectDateString);
        }

        public void nextObject()
        {
            counter++;
            this.requestData(objectIdArrayList.get(counter));
        }
        public void previousObject()
        {
            counter--;
            this.requestData(objectIdArrayList.get(counter));
        }

        @Override
        public void onFailure(Call<METObjectData> call, Throwable t)
        {
            t.printStackTrace();
        }
    }
}
