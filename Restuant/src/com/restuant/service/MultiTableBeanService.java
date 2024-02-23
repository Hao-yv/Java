package com.restuant.service;

import com.restuant.dao.MultiTableBeanDao;
import com.restuant.doma.MultiTableBean;

import java.util.List;

/**
 * @author HaoyvZhang
 * @version 1.0
 * @date 2024/2/23 11:07
 * @description:
 */
public class MultiTableBeanService {
    private MultiTableBeanDao multiTableBeanDao = new MultiTableBeanDao();
    // 查找菜单和订单
    public List<MultiTableBean> list(){
        return multiTableBeanDao.queryMulitply("select b.*, m.name from bill as b, menu as m where b.menuID = m.id;",
                MultiTableBean.class);
    }
}
