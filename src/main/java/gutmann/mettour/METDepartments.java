package gutmann.mettour;

import java.util.List;

public class METDepartments
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

        @Override
        public String toString()
        {
            return displayName;
        }
    }


    public String[] getArrayOfDisplayNames()
    {
        String[] arrayOfDisplayNames = new String[departments.size()];

        for (int i = 0; i < departments.size(); i++)
        {
            arrayOfDisplayNames[i] = departments.get(i).getDisplayName();
        }
        return arrayOfDisplayNames;
    }


}
