package gutmann.mettour;

import java.util.ArrayList;
import java.util.List;

public class METDepartments
{
    ArrayList<Department> departmentList = new ArrayList<>();

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

    public String[] getArrayOfDisplayNames()
    {
        String[] arrayOfDisplayNames = new String[departmentList.size()];

        for (int i = 0; i < departmentList.size(); i++)
        {
            arrayOfDisplayNames[i] = departmentList.get(i).getDisplayName();
        }
        return arrayOfDisplayNames;
    }


}
