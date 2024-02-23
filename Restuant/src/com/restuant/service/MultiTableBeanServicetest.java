package com.restuant.service;

import com.restuant.dao.MultiTableBeanDao;
import com.restuant.doma.MultiTableBean;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/23 11:14
 * @description:
 */
public class MultiTableBeanServicetest {
    private MultiTableBeanService multiTableBeanService = new MultiTableBeanService();

    // 查找菜单和订单
    @Test
    public void test1() {
        System.out.println("\n编号\t\t菜品号\t菜品量\t菜名\t\t\t金额\t\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
        Iterator iterator = multiTableBeanService.list().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
