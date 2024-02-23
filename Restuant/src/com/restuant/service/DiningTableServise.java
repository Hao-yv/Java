package com.restuant.service;

import com.restuant.dao.DiningTableDao;
import com.restuant.doma.DiningTable;

import java.awt.*;
import java.util.List;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 16:48
 * @description: 餐桌状态语句
 */
public class DiningTableServise {
    // 定义DingTableServiseDao 对象
    private DiningTableDao diningTableDao = new DiningTableDao();

    // 返回所有餐桌信息
    public List<DiningTable> list(){
        return diningTableDao.queryMulitply("select id, state from dindingTable", DiningTable.class);
    }

    // 根据id返回对应餐桌的DindingTable 对象，若返回 null， 则对应餐桌不存在
    public DiningTable getDiningTable(int id){
        return diningTableDao.querySingle("select * from dindingTable where id = ?", DiningTable.class, id);
    }

    // 如果餐桌可以预定，调用方法对其状态更新
    public boolean orderDiningTable(int id, String orderName, String orderTel){
        int update =
                diningTableDao.dml("update dindingTable set state = ‘已经预定’, orderName = ?, orderTel = ? where id = ?", orderName, orderTel, id);
        return update > 0;
    }

    // 提供一个更新餐桌状态的方法
    public boolean updateDiningTableStade(int id, String state){
        int update = diningTableDao.dml("update dindingTable set state = ? where id = ?", state, id);
        return update > 0;
    }

    // 提供一个方法， 将指定餐桌设为空闲状态
    public boolean updateDiningTableToFree(int id, String state){
        int dml = diningTableDao.dml("update dindingTable set state = ?, orderName = '', orderTel = '' where id = ?", state, id);
        return dml > 0;
    }
}
