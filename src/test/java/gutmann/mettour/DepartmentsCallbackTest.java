package gutmann.mettour;

import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DepartmentsCallbackTest
{
    @Test
    public void requestData()
    {
        //given
        METService service = Mockito.mock(METService.class);
        METObjectImageView objectImageView = Mockito.mock(METObjectImageView.class);
        METController controller = new METController(service, objectImageView);
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
        METService service = Mockito.mock(METService.class);
        METObjectImageView objectImageView = Mockito.mock(METObjectImageView.class);
        METController controller = new METController(service, objectImageView);
        METController.DepartmentsCallback departmentsCallback = controller.departmentsCallback;
        //a mocked METDepartment class
        METDepartments departments = Mockito.mock(METDepartments.class);

        //call and response objects (arguments for departmentsCallback.onResponse() method)
        Call<METDepartments> call = Mockito.mock(Call.class);
        Response<METDepartments> response = Mockito.mock(Response.class);

        doReturn(departments).when(response).body();
        //a stubbed version for departments.getArrayOfDisplayNames() method
        String [] arrayOfDisplayNames = new String[]{"Art", "More Art", "Even More Art"};
        when(departments.getArrayOfDisplayNames()).thenReturn(arrayOfDisplayNames);

        //when
        departmentsCallback.onResponse(call,response);

        //then
        verify(departments).getArrayOfDisplayNames();
        assertNotNull(controller.displayNamesComboBox);
    }
}