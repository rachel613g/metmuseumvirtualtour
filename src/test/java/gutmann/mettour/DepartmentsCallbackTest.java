package gutmann.mettour;


import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;
import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class DepartmentsCallbackTest
{
    @Test
    public void requestData()
    {
        //given
        //an instantiated controller
        METFrame frame = Mockito.mock(METFrame.class);
        METService service = Mockito.mock(METService.class);
        METObjectImageIconView objectImageView = Mockito.mock(METObjectImageIconView.class);
        JComboBox<METDepartments.Department> comboBox = Mockito.mock(JComboBox.class);
        JLabel objectImageLabel = Mockito.mock(JLabel.class);
        JLabel noImageLabel = Mockito.mock(JLabel.class);
        JLabel objectIdLabel = Mockito.mock(JLabel.class);
        JLabel objectTitleLabel = Mockito.mock(JLabel.class);
        JLabel objectCultureLabel = Mockito.mock(JLabel.class);
        JLabel objectArtistLabel = Mockito.mock(JLabel.class);
        JLabel objectDateLabel = Mockito.mock(JLabel.class);
        METController controller = new METController(frame, service, objectImageView, comboBox,
                noImageLabel, objectImageLabel, objectIdLabel, objectTitleLabel, objectCultureLabel, objectArtistLabel, objectDateLabel);
        METController.DepartmentsCallback departmentsCallback = controller.departmentsCallback;

        Call<METDepartments> call = Mockito.mock(Call.class);

        doReturn(call).when(service).getListOfDepartments();

        //when
        departmentsCallback.requestData();

        //then
        verify(service).getListOfDepartments();
        verify(call).enqueue(departmentsCallback);
    }

    @Test
    public void onResponse()
    {
        //given
        //an instantiated controller
        METFrame frame = Mockito.mock(METFrame.class);
        METService service = Mockito.mock(METService.class);
        METObjectImageIconView objectImageView = Mockito.mock(METObjectImageIconView.class);
        JComboBox<METDepartments.Department> comboBox = Mockito.mock(JComboBox.class);
        JLabel objectImageLabel = Mockito.mock(JLabel.class);
        JLabel noImageLabel = Mockito.mock(JLabel.class);
        JLabel objectIdLabel = Mockito.mock(JLabel.class);
        JLabel objectTitleLabel = Mockito.mock(JLabel.class);
        JLabel objectCultureLabel = Mockito.mock(JLabel.class);
        JLabel objectArtistLabel = Mockito.mock(JLabel.class);
        JLabel objectDateLabel = Mockito.mock(JLabel.class);
        METController controller = new METController(frame, service, objectImageView, comboBox,
                noImageLabel, objectImageLabel, objectIdLabel, objectTitleLabel, objectCultureLabel, objectArtistLabel, objectDateLabel);
        METController.DepartmentsCallback departmentsCallback = controller.departmentsCallback;

        //call and response objects (arguments for departmentsCallback.onResponse() method)
        Call<METDepartments> call = Mockito.mock(Call.class);
        Response<METDepartments> response = Mockito.mock(Response.class);
        //list to save response.body().departments
        //Why can't this METDepartments.Department be made into a list?!
        //it works in the real world
        List<METDepartments.Department> departmentList = new ArrayList<>() ;

        doReturn(departmentList).when(response).body();
        //a stubbed version for departments.getArrayOfDisplayNames() method

        //when
        departmentsCallback.onResponse(call,response);

        //then
        verify(controller.departmentsCallback).populateJComboBox(departmentList);

    }
}