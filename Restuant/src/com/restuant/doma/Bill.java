package com.restuant.doma;

import java.util.Date;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 20:59
 * @description:账单 create table bill(
 * id int primary key  auto_increment comment '主键自增',
 * billed varchar(50) not null default '' comment '账单号',
 * menuID int not null default 0 comment '菜品编号',
 * nums int not null default 0 comment '点餐数',
 * money double not null default 0 comment '金额',
 * diningTableID int not null default 0 comment '餐桌',
 * billDate datetime not null comment '订单日期',
 * state varchar(50) not null default '' comment '结账状态'
 * )charset utf8 comment '账单表';
 */
public class Bill {
    private Integer id;
    private String billed;
    private Integer menuID;
    private String nums;
    private Double money;
    private Integer diningTableID;
    private Date billDate;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billed, String nums, Double money, Integer diningTableID, Date billDate, String state) {
        this.id = id;
        this.billed = billed;
        this.nums = nums;
        this.money = money;
        this.diningTableID = diningTableID;
        this.billDate = billDate;
        this.state = state;
    }

    public Bill(Integer id, String billed, Integer menuID, String nums, Double money, Integer diningTableID, Date billDate, String state) {
        this.id = id;
        this.billed = billed;
        this.menuID = menuID;
        this.nums = nums;
        this.money = money;
        this.diningTableID = diningTableID;
        this.billDate = billDate;
        this.state = state;
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

    @Override
    public String toString() {
        return id + "\t\t" +
                menuID + "\t\t" +
                nums + "\t\t" +
                money + "\t\t" +
                diningTableID + "\t\t" +
                billDate + "\t\t" +
                state + "\t\t"
                ;
    }
}
