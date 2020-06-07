package gutmann.mettour;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class METFrame extends JFrame
{

    JComboBox<METDepartments.Department> departmentJComboBox;
    JPanel jComboBoxPanel;
    JPanel objectDataPanel;
    JLabel objectImageLabel;
    JLabel objectIdLabel;
    JLabel objectTitleLabel;
    JLabel objectCultureLabel;
    JLabel objectArtistLabel;
    JLabel objectDateLabel;

    METServiceFactory factory;
    METService service;
    METObjectImageView objectImageView;
    METController controller;

    METDepartments.Department selectDepartment;
    ArrayList<Integer> objectIdsArrayList;
    public METFrame()
    {
        setSize(new Dimension(800, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jComboBoxPanel = new JPanel();
        departmentJComboBox = new JComboBox<>();
        departmentJComboBox.setPreferredSize(new Dimension(200,100));
        objectDataPanel = new JPanel();

        objectImageLabel = new JLabel();
        objectIdLabel = new JLabel();
        objectTitleLabel = new JLabel();
        objectCultureLabel = new JLabel();
        objectArtistLabel = new JLabel();
        objectDateLabel = new JLabel();

        //set labels and panels to the frame
        add(jComboBoxPanel, BorderLayout.NORTH);
        jComboBoxPanel.add(departmentJComboBox);
        add(objectImageLabel, BorderLayout.CENTER);
        add(objectDataPanel, BorderLayout.EAST);
        objectDataPanel.setPreferredSize(new Dimension(300, 300));
        objectDataPanel.setLayout(new BoxLayout(objectDataPanel, BoxLayout.Y_AXIS));
        objectDataPanel.add(objectTitleLabel);
        objectDataPanel.add(objectCultureLabel);
        objectDataPanel.add(objectArtistLabel);
        objectDataPanel.add(objectDateLabel);
        add(objectIdLabel, BorderLayout.WEST);

        factory = new METServiceFactory();
        service = factory.getInstance();
        objectImageView = new METObjectImageView();

        controller = new METController(service, objectImageView, departmentJComboBox,
                objectImageLabel, objectIdLabel, objectIdLabel, objectCultureLabel, objectArtistLabel, objectDateLabel);

        //add departmentsJComboBox to frame
        controller.departmentsCallback.requestData();




        //select department actionListener
        departmentJComboBox.addActionListener(actionEvent -> getSelectedDepartment());

        //display Object Data

    }

    private void getSelectedDepartment()
    {
        selectDepartment = (METDepartments.Department) departmentJComboBox.getSelectedItem();
        controller.objectIdsCallback.requestData(selectDepartment.departmentId);
    }

    public static void main(String[] args)
    {
        new METFrame().setVisible(true);
    }
}


