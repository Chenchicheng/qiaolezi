package com.smart.store.config;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * @author chenchicheng
 * @date 2022/5/12
 */
public class MyDialect extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}

