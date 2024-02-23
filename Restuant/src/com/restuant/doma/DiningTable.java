package com.restuant.doma;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 16:41
 * @description: 餐桌状态
 */
public class DiningTable {
    private Integer id; // id
    private String state; // 餐桌状态
    private String orderName; // 预定人
    private String orderTel; // 联系方式

    public DiningTable() {
    }

    public DiningTable(Integer id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    @Override
    public String toString() {
        return id + "\t\t\t" + state;
    }
}
