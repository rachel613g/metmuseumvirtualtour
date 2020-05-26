import java.util.HashMap;
import java.util.List;

public class METDepartmentsList
{
    List<Department> departments;

    public class Department
    {
        int departmentId;
        String displayName;

        public int getDepartmentId()
        {
            return departmentId;
        }

        public String getDisplayName()
        {
            return displayName;
        }
    }
}
