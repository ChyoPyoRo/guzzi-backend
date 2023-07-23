package guzzi.user;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3306/guzzi?useSSL=false&allowPublicKeyRetrieval=true");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3306/guzzi?allowPublicKeyRetrieval=true&useSSL=false");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false");
//        config.setJdbcUrl("jdbc:mariadb://localhost:3306/guzzi?useSSL=false");
        config.setUsername("root");
//        config.setPassword("root1234");
        config.setPassword("1");
        HikariDataSource dataSource = new HikariDataSource(config);
//        dataSource.setUsername("root");
//        dataSource.setPassword("root1234");
        return dataSource;
        //dataUser guzzi Client does not support authentication protocol
        //         root   SQLInvalidAuthorizationSpecException
    }
}