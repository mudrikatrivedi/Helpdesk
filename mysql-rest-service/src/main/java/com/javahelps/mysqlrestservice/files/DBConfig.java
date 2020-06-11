package com.javahelps.mysqlrestservice.files;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.hibernate.boot.model.relational.Database;
import javax.persistence.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
@Configuration
@EnableJpaRepositories("com.javahelps.mysqlrestservice")
public class DBConfig{
	@Bean
    public DataSource dataSource(){
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/helpdesk?user=root&password=1234");
       dataSource.setUsername( "root" );
      dataSource.setPassword( "1234" );
       return dataSource;
    
}
	@Bean
    NamedParameterJdbcOperations operations() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
 
    @Bean
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}