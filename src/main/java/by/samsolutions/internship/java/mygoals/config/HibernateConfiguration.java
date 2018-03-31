package by.samsolutions.internship.java.mygoals.config;

import by.samsolutions.internship.java.mygoals.dao.hibernate.GoalDaoImpl;
import by.samsolutions.internship.java.mygoals.dao.hibernate.StageDaoImpl;
import by.samsolutions.internship.java.mygoals.dao.hibernate.TaskDaoImpl;
import by.samsolutions.internship.java.mygoals.dao.hibernate.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:jdbc.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("by.samsolutions.internship.java.mygoals");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    @Bean
    @Autowired
    public UserDaoImpl userDao(SessionFactory s) {
        final UserDaoImpl userDao = new UserDaoImpl();
        userDao.setSessionFactory(s);
        return userDao;
    }

    @Bean
    @Autowired
    public GoalDaoImpl goalDao(SessionFactory s) {
        final GoalDaoImpl goalDao = new GoalDaoImpl();
        goalDao.setSessionFactory(s);
        return goalDao;
    }

    @Bean
    @Autowired
    public StageDaoImpl stageDao(SessionFactory s) {
        final StageDaoImpl stageDao = new StageDaoImpl();
        stageDao.setSessionFactory(s);
        return stageDao;
    }

    @Bean
    @Autowired
    public TaskDaoImpl taskDao(SessionFactory s) {
        final TaskDaoImpl taskDao = new TaskDaoImpl();
        taskDao.setSessionFactory(s);
        return taskDao;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
