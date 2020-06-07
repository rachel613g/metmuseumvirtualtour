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

}
