package com.restuant.service;

import com.restuant.dao.MenuDao;
import com.restuant.doma.Menu;

import java.util.List;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/22 20:02
 * @description:菜单
 */
public class MenuServise {
    private MenuDao menuDao = new MenuDao();

    // 显示所有菜品
    public List<Menu> getMenus(){
        return menuDao.queryMulitply("select * from menu", Menu.class);
    }

    // 根据id 返回对应的Menu 对象，若返回null 则对应菜品不存在
    public Menu getMenuById(int id){
        return menuDao.querySingle("select * from menu where id = ?", Menu.class, id);
    }

}
