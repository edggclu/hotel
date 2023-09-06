package ed.mx.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource pooledFactory = new ComboPooledDataSource();
        pooledFactory.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZo0ne=true&serverTimeZone=UTC");
        pooledFactory.setUser("root");
        pooledFactory.setPassword("1234");

        this.dataSource = pooledFactory;
    }
    public Connection recuperarConexion(){
        try{
            return this.dataSource.getConnection();
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}
