package com.restuant.service;

import com.restuant.dao.BillDao;
import com.restuant.doma.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 21:03
 * @description:
 */
public class BillService {
    private BillDao billDao = new BillDao();
    // 定义一个MenuService 属性
    private MenuServise menuServise = new MenuServise();
    private DiningTableServise diningTableServise = new DiningTableServise();

    // 点菜更新
    public boolean orderMenu(int menuID, int nums, int dindingTableID){
        // 生成账单号
        String billID = UUID.randomUUID().toString();

        // 将账单生成, 要求直接计算mone值
        int update = billDao.dml("insert into bill values(null, ?, ?, ?, ?,?, now(), '未结账')"
                , billID, menuID, nums, menuServise.getMenuById(menuID).getPrice() * nums, dindingTableID);

        if(update <= 0){
            return false;
        }

        // 更新餐桌状态
        return diningTableServise.updateDiningTableStade(dindingTableID, "就餐中");

    }

    // 返回所有账单对象
    public List<Bill> list(){
        return billDao.queryMulitply("select * from bill", Bill.class);
    }

    // 查看某个餐桌是否有未结账账单
    public boolean hasPayBillByDindingTableID(int diningTableID){
        Bill bill = billDao.querySingle("select * from bill where diningTableID = ? and state = '未结账' limit 0, 1", Bill.class, diningTableID);
        return bill != null;
    }

    // 完成结账
    public boolean payBill(int diningTaleID, String payMode){
        // 1. 修改bill 表
        int dml = billDao.dml("update bill set state = ? where diningTableID = ? and state = '未结账'", payMode, diningTaleID);
        if(dml <= 0){ // 若没有更新成功则失败
            return false;
        }
        if(!diningTableServise.updateDiningTableToFree(diningTaleID, "空")){
            return false;
        }

        return true;
    }
}
