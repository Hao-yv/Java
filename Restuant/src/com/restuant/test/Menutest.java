package com.restuant.test;

import com.restuant.service.MenuServise;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 20:24
 * @description:
 */
public class Menutest {
    private MenuServise menuServise = new MenuServise();

    @Test
    public void test() {
        System.out.println("\n菜品编号\t菜品名\t\t类别\t\t价格");
        Iterator iterator = menuServise.getMenus().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
