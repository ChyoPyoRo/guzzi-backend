package guzzi.project;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
// spring 2.x.x 버전이라 @mapper annotation 사용하지 못한다.
// mapper 대신 dao를 통해 xml 을 주입해야한다.


    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        //jdbc driver config setting
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        // db 접속정보
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false");
        config.setUsername("root");
        config.setPassword("1");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath*:mybatis/SqlMapConfig.xml"));
        factoryBean.setMapperLocations(applicationContext.getResource("classpath*:mapper/*.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}