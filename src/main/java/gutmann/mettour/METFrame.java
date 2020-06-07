package gutmann.mettour;

import javax.swing.*;
import java.awt.*;

public class METFrame extends JFrame
{
    METServiceFactory factory;
    METService service;
    METObjectImageView objectImageView;
    METController controller;
    METDepartments.Department selectDepartment;
    JComboBox<METDepartments.Department> departmentJComboBox;
    JPanel comboPanel;

    public METFrame()
    {
        setSize(new Dimension(500, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        factory = new METServiceFactory();
        service = factory.getInstance();
        objectImageView = new METObjectImageView();
        departmentJComboBox = new JComboBox<>();
        departmentJComboBox.setPreferredSize(new Dimension(200,100));

        controller = new METController(service, objectImageView, departmentJComboBox);

        //add departmentsJComboBox to frame
        controller.departmentsCallback.requestData();
        comboPanel = new JPanel();
        comboPanel.add(departmentJComboBox);
        add(comboPanel);

        //select department actionListener
        departmentJComboBox.addActionListener(actionEvent -> getSelectedDepartment());

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


