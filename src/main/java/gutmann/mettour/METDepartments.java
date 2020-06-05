package gutmann.mettour;

import java.util.List;

public class METDepartments
{
    List<Department> departmentList;

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

    public String[] getArrayofDisplayNames()
    {
        String[] arrayOfDisplayNames = new String[departmentList.size()];

        for (int i = 0; i > departmentList.size(); i++)
        {
            arrayOfDisplayNames[i] = departmentList.get(i).getDisplayName();
        }

        return arrayOfDisplayNames;
    }
}
