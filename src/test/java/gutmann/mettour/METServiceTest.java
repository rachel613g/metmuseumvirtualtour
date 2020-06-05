package gutmann.mettour;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.*;

public class METServiceTest
{

    @Test
    public void getListOfDepartments() throws IOException
    {
        //given
        METServiceFactory factory = new METServiceFactory();
        METService service = factory.getInstance();

        //when
        Response<METDepartments> response = service.getListOfDepartments().execute();
        METDepartments departmentsList = response.body();

        //then
        assertNotNull(departmentsList);
        assertNotNull(departmentsList.departmentList);
    }

    @Test
    public void getObjectIdsInDepartment() throws IOException
    {
        //given
        METServiceFactory factory = new METServiceFactory();
        METService service = factory.getInstance();

        //when
        Response<METObjectIds> response = service.getObjectIdsInDepartment(3).execute();
        METObjectIds objectIds = response.body();

        //then
        assertNotNull(objectIds);
    }

    @Test
    public void getMetaData() throws IOException
    {
        //given
        METServiceFactory factory = new METServiceFactory();
        METService service = factory.getInstance();

        //when
        Response<METObjectData> response = service.getMetaData(462704).execute();
        METObjectData objectData = response.body();

        //then
        assertNotNull(objectData);
    }
}