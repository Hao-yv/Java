package com.restuant.dao;


import com.restuant.utils.DButils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class basicDao<T>{ // 泛型指定具体类型
    private QueryRunner qr = new QueryRunner();

    // 通用bml操作,针对任意报表
    public int dml(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = DButils.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(null, null, connection);
        }
    }

    // 返回多个对象
    public List<T> queryMulitply(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;
        try {
            connection = DButils.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(null, null, connection);
        }
    }

    // 返回单行结果
    public T querySingle(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;
        try {
            connection = DButils.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DButils.close(null, null, connection);
        }
    }

    // 查询单值方法
    public Object queryScanner(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;
        try {
            connection =  DButils.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
