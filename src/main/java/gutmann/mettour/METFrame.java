package gutmann.mettour;

import javax.swing.*;
import java.awt.*;

public class METFrame extends JFrame
{
    METServiceFactory factory;
    METService service;
    METObjectImageView objectImageView;
    METController controller;

    JFrame ObjectDataFrame;
    JComboBox<String> displayNamesJComboBox;
    JPanel comboPanel;

    public METFrame()
    {
        setSize(new Dimension(500, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        factory = new METServiceFactory();
        service = factory.getInstance();
        objectImageView = new METObjectImageView();
        displayNamesJComboBox = new JComboBox<>();

        displayNamesJComboBox.setPreferredSize(new Dimension(200,200));
        controller = new METController(service, objectImageView, displayNamesJComboBox);

        controller.departmentsCallback.requestData();
        comboPanel.add(displayNamesJComboBox);
        displayNamesJComboBox.setSelectedIndex(0);
        add(comboPanel);
    }

    public static void main(String[] args)
    {
        new METFrame().setVisible(true);
    }
}


