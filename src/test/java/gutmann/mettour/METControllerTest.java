package gutmann.mettour;

import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;

import javax.swing.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class METControllerTest
{
    @Test
    public void metControllerDepartmentListRequestDataTest()
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

        //
        verify(service).getListOfDepartments();
        verify(call).enqueue(departmentsCallback);
    }
}