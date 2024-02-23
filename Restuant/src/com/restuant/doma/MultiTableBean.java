package com.restuant.doma;

import java.util.Date;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/23 10:49
 * @description:
 */
public class MultiTableBean {
    private Integer id;
    private String billed;
    private Integer menuID;
    private String nums;
    private Double money;
    private Integer diningTableID;
    private Date billDate;
    private String state;
    private String name;

    public MultiTableBean() {
    }

    public MultiTableBean(Integer id, String billed, Integer menuID, String nums, Double money, Integer diningTableID, Date billDate, String state, String name) {
        this.id = id;
        this.billed = billed;
        this.menuID = menuID;
        this.nums = nums;
        this.money = money;
        this.diningTableID = diningTableID;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBilled() {
        return billed;
    }

    public void setBilled(String billed) {
        this.billed = billed;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableID() {
        return diningTableID;
    }

    public void setDiningTableID(Integer diningTableID) {
        this.diningTableID = diningTableID;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "\t\t" +
                menuID + "\t\t" +
                nums + "\t\t" +
                name +"\t\t" +
                money + "\t\t" +
                diningTableID + "\t\t" +
                billDate +"\t\t" +
                state + "\t\t"
                ;
    }
}
