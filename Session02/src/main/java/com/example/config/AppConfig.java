package com.example.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc

// Kích hoạt việc quản lý repository JPA trong gói "rikkei.academy.repository".
// Điều này sử dụng Spring Data JPA để quản lý các repository và tạo ra các bean repository cần thiết.
@EnableJpaRepositories("com.example.model.dao.student")
@ComponentScan(basePackages = {"com.example"}) //tu dong quet qua cac thanh phan anh xa ra view
//@PropertySource("classpath:config.properties")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {
//    @Value("${path}")
//    private String path;
//
//    @Value("${user}")
//    private String user;

    private ApplicationContext applicationContext;

    //cau hinh ngon ngu
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        messageSource.setBasename("lang");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver=new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("vi"));
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor changeInterceptor=new LocaleChangeInterceptor();
        changeInterceptor.setParamName("lang");
        registry.addInterceptor(changeInterceptor);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/md05_session02");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456a@");
        return driverManagerDataSource;
    }

    // Định nghĩa các thuộc tính cấu hình cho Hibernate,
    // chẳng hạn như hibernate.hbm2ddl.auto (chế độ tạo bảng),
    // hibernate.dialect (ngôn ngữ của cơ sở dữ liệu).
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    //  tương tác với các đối tượng được quản lý của JPA.
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        //tạo ra EntityManagerFactory. Nó giúp quản lý các entity, xử lý giao dịch và cung cấp một loạt các tính năng của JPA.
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        //Xác định DataSource mà EntityManagerFactory sẽ sử dụng để kết nối đến cơ sở dữ liệu
        entityManagerFactoryBean.setDataSource(dataSource());
        // Chỉ định các package cần được quét để tìm kiếm các entity
        entityManagerFactoryBean.setPackagesToScan("com.example.model.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //thông báo rằng Hibernate sẽ được sử dụng làm cài đặt JPA trong ứng dụng
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        //Xác định các thuộc tính cấu hình cho Hibernate thông qua phương thức additionalProperties().
        // Các thuộc tính này có thể bao gồm chế độ tạo bảng (hibernate.hbm2ddl.auto), ngôn ngữ của cơ sở dữ liệu
        entityManagerFactoryBean.setJpaProperties(additionalProperties());
        // Trả về đối tượng LocalContainerEntityManagerFactoryBean đã được cấu hình,
        // sẵn sàng để được sử dụng bởi Spring Container và các thành phần khác trong ứng dụng.
        return entityManagerFactoryBean;
    }

    //sử dụng để quản lý giao dịch trong ứng dụng, đặc biệt là khi sử dụng JPA
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


//    @Bean
//    public LocalSessionFactoryBean sessionFactoryBean() {
//        LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource());
//        localSessionFactoryBean.setPackagesToScan("com.example.model.entity");
//        Properties properties=new Properties();
//        properties.setProperty("hibernate.show_sql","true");
//        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
//        localSessionFactoryBean.setHibernateProperties(properties);
//        return localSessionFactoryBean;
//    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }


    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(templateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    //cau hinh upload file
    @Bean
    CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(52428800);
        return multipartResolver;
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/fonts/**", "/images/**", "/img/**", "/js/**", "/libs/**", "/webfonts/**", "/uploads/images/**")
                .addResourceLocations("file:/Users/admin/Desktop/PROJECT_MD4/src/main/webapp/uploads/images/", "classpath:assets/admin/css/", "classpath:assets/admin/fonts/", "classpath:assets/admin/images/", "classpath:assets/admin/js/", "classpath:assets/admin/libs/",
                        "classpath:assets/client/css/", "classpath:assets/client/fonts/", "classpath:assets/client/images/", "classpath:assets/client/img/", "classpath:assets/client/js/", "classpath:assets/client/webfonts/");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//      registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/admin/**");
//    }
}