import org.junit.Test;

import static org.junit.Assert.*;

public class METServiceFactoryTest
{

    @Test
    public void getInstance()
    {
        //given
        METServiceFactory factory = new METServiceFactory();
        //when
        METService service = factory.getInstance();
        //then
        assertNotNull(service);
    }
}