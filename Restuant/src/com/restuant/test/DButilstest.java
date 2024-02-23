package com.restuant.test;

import com.restuant.utils.DButils;

import java.sql.Connection;

public class DButilstest {
    public static void main(String[] args) {
        Connection connection = DButils.getConnection();
        System.out.println(connection);
    }
}
