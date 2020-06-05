package gutmann.mettour;

import javax.swing.*;
import java.awt.*;

public class METFrame extends JFrame
{
    JFrame ObjectDataFrame;
    JComboBox<String> departmentListComboBox;

    public METFrame()
    {
        setSize(new Dimension(300, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //initialize controller by initializing factory, service and view objects
        METServiceFactory factory = new METServiceFactory();
        METService service = factory.getInstance();
        METObjectImageView objectImageView = new METObjectImageView();
        METController controller = new METController(service, objectImageView);

        controller.departmentsCallback.requestData(departmentListComboBox);


    }

    public static void main(String[] args)
    {
        new METFrame().setVisible(true);
    }
}


