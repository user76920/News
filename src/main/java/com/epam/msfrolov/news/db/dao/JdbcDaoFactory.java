package com.epam.msfrolov.news.db.dao;

import com.epam.msfrolov.news.db.pool.DBConnectionPool;
import com.epam.msfrolov.news.exception.AppException;

import java.sql.Connection;

public class JdbcDaoFactory implements DaoFactory {

    private final Connection connection;

    public JdbcDaoFactory() {
        connection = DBConnectionPool.getConnection();
    }

    @Override
    public NewsDao getDao() {
        return new JdbcNewsDao(connection);
    }

    @Override
    public void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            throw new AppException("It failed to close or symbol not open", e);
        }
    }
}