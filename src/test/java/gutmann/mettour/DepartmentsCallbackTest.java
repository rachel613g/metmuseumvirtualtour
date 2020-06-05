package gutmann.mettour;

import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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
        JComboBox<String> comboBox = Mockito.mock(JComboBox.class);

        Call<METDepartments> call = Mockito.mock(Call.class);

        doReturn(call).when(service).getListOfDepartments();

        //when
        departmentsCallback.requestData(comboBox);

        //then
        verify(service).getListOfDepartments();
        verify(call).enqueue(departmentsCallback);
    }

    @Test
    public void onResponse()
    {
        METService service = Mockito.mock(METService.class);
        METObjectImageView objectImageView = Mockito.mock(METObjectImageView.class);
        METController controller = new METController(service, objectImageView);
        METController.DepartmentsCallback departmentsCallback = controller.departmentsCallback;
        //JComboBox<String> comboBox = departmentsCallback.displayNamesComboBox;
        METDepartments departments = Mockito.mock(METDepartments.class);

        Call<METDepartments> call = Mockito.mock(Call.class);
        Response<METDepartments> response = Mockito.mock(Response.class);

        doReturn(departments).when(response).body();
    }
}