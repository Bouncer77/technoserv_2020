package com.kosenkov;

import java.util.List;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 13.07.2020
 * task 1 part 2
 */

public class Company {
    String companyName; //  = inputFileName
    List<Department> departmentList;

    public Company(List<Department> departmentList, String companyName) {
        this.departmentList = departmentList;
        this.companyName = companyName;
    }

    /* Метод поиска отдела в списке отделов компании */
    public boolean hasDepartment(String departmentName) {
        for (Department dep : departmentList) {
            if (dep.getDepartmentName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }
}
