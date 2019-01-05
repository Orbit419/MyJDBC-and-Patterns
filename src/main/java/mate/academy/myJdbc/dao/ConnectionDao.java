package mate.academy.myJdbc.dao;

import java.sql.Connection;

public abstract class ConnectionDao {
    final Connection connection;

    public ConnectionDao(Connection connection) {
        this.connection = connection;
    }
}
