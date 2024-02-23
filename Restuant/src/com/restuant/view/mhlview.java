package com.restuant.view;

import com.restuant.doma.DiningTable;
import com.restuant.doma.Employee;
import com.restuant.doma.Menu;
import com.restuant.service.*;
import com.restuant.utils.DButils;
import com.restuant.utils.Utility;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 14:42
 * @description: 主界面
 */
public class mhlview {
    // 控制是否退出菜单
    private boolean loop = true;
    private String key = "";// 接受用户选择

    // 定义EmployeeService 对象
    private EmployeeServise employeeServise = new EmployeeServise();

    // 定义 DingTableServise 对象
    private DiningTableServise diningTableServise = new DiningTableServise();

    // 定义 MenuServise 对象
    private MenuServise menuServise = new MenuServise();

    // 定义 BillServise 对象
    private BillService billService = new BillService();

    // 定义 MultiTableBeanService 对象
    private MultiTableBeanService multiTableBeanService = new MultiTableBeanService();

    // 完成订座
    public void orderDiningTable(){
        System.out.println("=========预定餐桌=========");
        System.out.println("选择要预定的餐桌编号（-1退出）");
        int orderID = Utility.readInt();
        if(orderID == -1){
            System.out.println("=======取消预定餐桌========");
            return;
        }
        char key = Utility.readConfirmSelection();
        if(key == 'Y'){
            // 根据id返回
            DiningTable diningTable = diningTableServise.getDiningTable(orderID);
            if(diningTable == null){
                System.out.println("预定餐桌不存在");
                return;
            }
            // 判断该餐桌的状态
            if(!"空".equals(diningTable.getState())){
                System.out.println("当前餐桌以被占用");
                return;
            }
            // 更新餐桌状态
            System.out.println("预定人姓名");
            String orderName = Utility.readString(50);
            System.out.println("预定人电话");
            String orderTel = Utility.readString(50);
            if (diningTableServise.orderDiningTable(key, orderName, orderTel)){
                System.out.println("预定成功");
            }else {
                System.out.println("预定失败");
            }

        }else {
            System.out.println("=======取消预定餐桌========");
        }

    }

    // 完成点餐
    public void orderMenu(){
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

    // 完成结账
    public void payBill(){
        System.out.println("==========结账服务=========");
        System.out.println("选择要结账的餐桌编号");
        int diningTableID = Utility.readInt();
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
        String payMode = Utility.readString(20, ""); // 若回车则返回空字符串
        if("".equals(payMode)){
            System.out.println("取消结账");
            return;
        }
        char Key = Utility.readConfirmSelection();
        if(Key == 'Y'){
            if (billService.payBill(diningTableID, payMode)){
                System.out.println("结账成功");
            }
        }else {
            System.out.println("取消结账");
        }


    }

    // 显示主菜单
    public void mainMenu(){
        while (loop){
            System.out.println("==========满汉楼==========");
            System.out.println("\t\t     1. 登录");
            System.out.println("\t\t     2. 退出");
            System.out.println("输入你的选择");
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("输入员工号");
                    String empID = Utility.readString(50);
                    System.out.println("输入密  码");
                    String pwd = Utility.readString(50);
                    // 到数据库判断

                    Employee employee = employeeServise.getEmployeeBYIDAndPwd(empID, pwd);
                    if(employee != null){
                        System.out.println("=========登录成功=========\n");
                        System.out.println(employee.getName());
                        // 显示二级菜单
                        // 二级菜单也是循环操作
                        while (loop){
                            System.out.println("==========满汉楼（二级菜单）==========");
                            System.out.println("\t\t 1. 显示餐桌状态");
                            System.out.println("\t\t 2. 预定餐桌");
                            System.out.println("\t\t 3. 显示所有菜品");
                            System.out.println("\t\t 4. 点餐服务");
                            System.out.println("\t\t 5. 查看账单");
                            System.out.println("\t\t 6. 结账");
                            System.out.println("\t\t 7. 退出满汉楼");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    System.out.println("显示餐桌状态");
                                    List<DiningTable> list = diningTableServise.list();
                                    System.out.println("\n餐桌编号\t\t餐桌状态");
                                    for(DiningTable diningTable : list){
                                        System.out.println(diningTable);
                                    }
                                    System.out.println();

                                    break;
                                case "2":
                                    orderDiningTable();
                                    break;
                                case "3":
                                    System.out.println("\n菜品编号\t菜品名\t\t类别\t\t价格");                                    List<Menu> menu = menuServise.getMenus();
                                    for (Menu menu1 : menu){
                                        System.out.println(menu1);
                                    }
                                    System.out.println();
                                    break;
                                case "4":
                                    System.out.println("点餐服务");
                                    break;
                                case "5":
                                    System.out.println("\n菜品编号\t菜品名\t\t类别\t\t价格");
                                    Iterator iterator = menuServise.getMenus().iterator();
                                    while (iterator.hasNext()){
                                        System.out.println(iterator.next());
                                    }
                                    break;
                                case "6":
                                    System.out.println("结账");
                                    payBill();
                                    break;
                                case "7":
                                    System.out.println("退出满汉楼");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("请重新输入");
                                    break;
                            }
                        }
                    }else {
                        System.out.println("=========登录失败=========");
                    }
                    break;
                case "2":
                    System.out.println("退出满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }
    }
}
