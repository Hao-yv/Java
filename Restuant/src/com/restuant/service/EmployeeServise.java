package com.restuant.service;

import com.restuant.dao.EmployDao;
import com.restuant.doma.Employee;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 15:48
 * @description: 业务层，组织sql语句并调用相应dao
 */
public class EmployeeServise {

    // 定义一个Employee属性
    private EmployDao employDao = new EmployDao();

    // 根据empID 和 pwd 返回Employee对象
    // 如果查询不到就返回空
    // 可根据返回值是否为空得到用户是否存在
    public Employee getEmployeeBYIDAndPwd(String empID, String pwd){
         return employDao.querySingle("select * from employee where empID = ? and pwd = md5(?)", Employee.class, empID, pwd);
    }
}
