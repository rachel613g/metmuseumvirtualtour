package gutmann.mettour;

import org.junit.Test;

import static org.junit.Assert.*;

public class METDepartmentsTest
{

    @Test
    public void getArrayOfDisplayNames()
    {
        //given
        //populated METDepartment and Depart objects
        METDepartments departments = new METDepartments();

        METDepartments.Department department1 = departments. new Department();
        //I begin with three because that is how ArrayList works
        department1.departmentId = 3;
        department1.displayName = "Art";
        METDepartments.Department department2 = departments. new Department();
        department2.departmentId = 2;
        department2.displayName = "More Art";
        METDepartments.Department department3 = departments. new Department();
        department3.departmentId = 1;
        department3.displayName = "Even More Art";

        departments.departments.add(department1);
        departments.departments.add(department2);
        departments.departments.add(department3);

        //when
        String [] returnArray = departments.getArrayOfDisplayNames();

        //then
        String [] assertArray = {"Art", "More Art", "Even More Art"};

        assertArrayEquals(assertArray,returnArray);
    }
}