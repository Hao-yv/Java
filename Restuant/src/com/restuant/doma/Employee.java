package com.restuant.doma;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 15:44
 * @description: 这是javabean 和 employee对应
 */
public class Employee {
    private Integer id;
    private String empID;
    private String pwd;
    private String name;
    private String job;

    public Employee() {// 无参构造器，底层 apache-dbutils 反射需要
    }

    public Employee(Integer id, String empID, String pwd, String name, String job) {
        this.id = id;
        this.empID = empID;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
