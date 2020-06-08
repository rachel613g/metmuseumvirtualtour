package gutmann.mettour;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class METFrame extends JFrame
{

    JComboBox<METDepartments.Department> departmentJComboBox;
    JPanel jComboBoxPanel;
    JPanel objectDataPanel;
    JPanel arrowPanel;
    JButton selectDepartmentButton;
    JLabel objectImageLabel;
    JLabel noImageLabel;
    JLabel objectIdLabel;
    JLabel objectTitleLabel;
    JLabel objectCultureLabel;
    JLabel objectArtistLabel;
    JLabel objectDateLabel;

    JButton nextButton;
    JButton prevButton;

    METServiceFactory factory;
    METService service;
    METObjectImageIconView objectImageView;
    METController controller;

    METDepartments.Department selectDepartment;
    ArrayList<Integer> objectIdsArrayList;
    public METFrame()
    {
        setSize(new Dimension(800, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("MET Museum Tour");

        jComboBoxPanel = new JPanel(new FlowLayout());
        departmentJComboBox = new JComboBox<>();
        departmentJComboBox.setPreferredSize(new Dimension(300,50));
        selectDepartmentButton = new JButton("Select Department");
        objectDataPanel = new JPanel();
        objectDataPanel.setPreferredSize(new Dimension(200, 200));
        objectDataPanel.setLayout(new BoxLayout(objectDataPanel, BoxLayout.Y_AXIS));
        objectImageLabel = new JLabel();
        objectImageLabel.setPreferredSize(new Dimension(300, 300));
        noImageLabel = new JLabel();
        objectIdLabel = new JLabel();
        objectTitleLabel = new JLabel();
        objectCultureLabel = new JLabel();
        objectArtistLabel = new JLabel();
        objectDateLabel = new JLabel();
        arrowPanel = new JPanel();
        nextButton = new JButton("Next>>>>>>");
       // nextButton.setPreferredSize(new Dimension(150,150));
        prevButton = new JButton("<<<<<<Previous");
        //prevButton.setPreferredSize(new Dimension(150,150));

        //set labels and panels to the frame
        jComboBoxPanel.add(departmentJComboBox);
        jComboBoxPanel.add(selectDepartmentButton);
        add(jComboBoxPanel, BorderLayout.NORTH);

        factory = new METServiceFactory();
        service = factory.getInstance();
        objectImageView = new METObjectImageIconView();

        controller = new METController(this, service, objectImageView, departmentJComboBox,
                objectImageLabel, noImageLabel, objectIdLabel, objectTitleLabel, objectCultureLabel,
                objectArtistLabel, objectDateLabel);

        //add departmentsJComboBox to frame
        controller.departmentsCallback.requestData();

        //select department actionListener
        selectDepartmentButton.addActionListener(actionEvent -> getSelectedDepartment());
        selectDepartmentButton.addActionListener(actionEvent -> setArrowPanel());

        //add object data components to frame
        add(objectImageLabel, BorderLayout.AFTER_LINE_ENDS);
        objectDataPanel.add(objectTitleLabel);
        objectDataPanel.add(noImageLabel);
        objectDataPanel.add(objectCultureLabel);
        objectDataPanel.add(objectArtistLabel);
        objectDataPanel.add(objectDateLabel);
        add(objectDataPanel, BorderLayout.SOUTH);
        add(objectIdLabel, BorderLayout.WEST);


        prevButton.addActionListener(actionEvent -> controller.objectDataCallback.previousObject());
        nextButton.addActionListener(actionEvent -> controller.objectDataCallback.nextObject());

    }

    private void getSelectedDepartment()
    {
        selectDepartment = (METDepartments.Department) departmentJComboBox.getSelectedItem();
        int departmentId = selectDepartment.departmentId;
        controller.objectIdsCallback.requestData(departmentId);
    }


    public void setArrowPanel()
    {
        arrowPanel.add(prevButton);
        arrowPanel.add(nextButton);
        add(arrowPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args)
    {
        new METFrame().setVisible(true);
    }
}


