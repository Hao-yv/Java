package com.restuant.test;

import com.restuant.doma.DiningTable;
import com.restuant.doma.Menu;
import com.restuant.service.BillService;
import com.restuant.service.DiningTableServise;
import com.restuant.service.MenuServise;
import com.restuant.service.MultiTableBeanService;
import com.restuant.utils.Utility;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 21:46
 * @description:
 */
public class Billtest {
    private MenuServise menuServise = new MenuServise();
    private DiningTableServise diningTableServise = new DiningTableServise();
    private  BillService billService = new BillService();
    private MultiTableBeanService multiTableBeanService = new MultiTableBeanService();
    @Test
    public void test(){
        System.out.println("==========点餐服务==========");
        System.out.println("输入点餐的桌号(-1退出)");
        int orderDiningTableID = Utility.readInt();
        if(orderDiningTableID == -1){
            System.out.println("取消点餐");
            return;
        }

        System.out.println("输入点餐的菜品号(-1退出)");
        int orderMenuID = Utility.readInt();
        if(orderMenuID == -1){
            System.out.println("取消点餐");
            return;
        }

        System.out.println("输入点餐的菜品号(-1退出)");
        int orderNums = Utility.readInt();
        if(orderNums == -1){
            System.out.println("取消点餐");
            return;
        }

        // 验证餐桌号是否存在

        DiningTable diningTable = diningTableServise.getDiningTable(orderDiningTableID);
        if(diningTable == null){
            System.out.println("餐桌号有误");
            return;
        }

        // 验证菜品是否存在

        Menu menuById = menuServise.getMenuById(orderMenuID);
        if(menuById == null){
            System.out.println("菜单号有误");
            return;
        }

        // 点餐
        if(billService.orderMenu(orderMenuID, orderNums, orderDiningTableID)){
            System.out.println("点餐成功");
        }else {
            System.out.println("点餐失败");
        }
    }

    @Test
    public void test1(){
        System.out.println("\n编号\t\t菜品号\t菜品量\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        Iterator iterator = billService.list().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        System.out.println("==========结账服务=========");
        System.out.println("选择要结账的餐桌编号");
        int diningTableID = 2;
        if(diningTableID == -1){
            System.out.println("==========取消结账=========");
        }
        // 验证餐桌是否存在
        DiningTable diningTable = diningTableServise.getDiningTable(diningTableID);
        if(diningTable == null){
            System.out.println("=========餐桌不存在=========");
            return;
        }
        // 验证是否有需要结账账单
        if (!billService.hasPayBillByDindingTableID(diningTableID)){
            System.out.println("=========无未结账账单=========");
            return;
        }

        System.out.println("选择结账方式(现金, 支付宝， 微信)回车表示退出");
        String payMode = "现金"; // 若回车则返回空字符串
        if("".equals(payMode)){
            System.out.println("取消结账");
            return;
        }
        char Key = 'Y';
        if(Key == 'Y'){
            if (billService.payBill(diningTableID, payMode)){
                System.out.println("结账成功");
            }
        }else {
            System.out.println("取消结账");
        }
    }
}
