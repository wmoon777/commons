package com.lsl.commons.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.lsl.commons.config.boot.EnableCache;
import com.lsl.commons.config.boot.ImportRole;
import com.lsl.commons.config.boot.RoleImportBeanDefinitionRegistrar;

/**
 * SpringApplication的run方法的实现是我们本次旅程的主要线路，该方法的主要流程大体可以归纳如下： 1）
 * 如果我们使用的是SpringApplication的静态run方法，那么，这个方法里面首先要创建一个SpringApplication对象实例，然后调用这个创建好的SpringApplication的实例方法。
 * 在SpringApplication实例初始化的时候，它会提前做几件事情：
 * 根据classpath里面是否存在某个特征类（org.springframework.web.context.ConfigurableWebApplicationContext）
 * 来决定是否应该创建一个为Web应用使用的ApplicationContext类型。
 * 使用SpringFactoriesLoader在应用的classpath中查找并加载所有可用的ApplicationContextInitializer。
 * 使用SpringFactoriesLoader在应用的classpath中查找并加载所有可用的ApplicationListener。 推断并设置main方法的定义类。 2）
 * SpringApplication实例初始化完成并且完成设置后，就开始执行run方法的逻辑了，方法执行伊始，首先遍历执行所有通过SpringFactoriesLoader可以查找到并加载的
 * SpringApplicationRunListener。调用它们的started()方法，告诉这些SpringApplicationRunListener，“嘿，SpringBoot应用要开始执行咯！”。 3）
 * 创建并配置当前Spring Boot应用将要使用的Environment（包括配置要使用的PropertySource以及Profile）。 4）
 * 遍历调用所有SpringApplicationRunListener的environmentPrepared()的方法，告诉他们：“当前SpringBoot应用使用的Environment准备好了咯！”。 5）
 * 如果SpringApplication的showBanner属性被设置为true，则打印banner。 6）
 * 根据用户是否明确设置了applicationContextClass类型以及初始化阶段的推断结果，决定该为当前SpringBoot应用创建什么类型的ApplicationContext并创建完成，
 * 然后根据条件决定是否添加ShutdownHook，决定是否使用自定义的BeanNameGenerator，决定是否使用自定义的ResourceLoader，当然，最重要的，
 * 将之前准备好的Environment设置给创建好的ApplicationContext使用。 7）
 * ApplicationContext创建好之后，SpringApplication会再次借助Spring-FactoriesLoader，
 * 查找并加载classpath中所有可用的ApplicationContext-Initializer，
 * 然后遍历调用这些ApplicationContextInitializer的initialize（applicationContext）方法来对已经创建好的ApplicationContext进行进一步的处理。 8）
 * 遍历调用所有SpringApplicationRunListener的contextPrepared()方法。 9）
 * 最核心的一步，将之前通过@EnableAutoConfiguration获取的所有配置以及其他形式的IoC容器配置加载到已经准备完毕的ApplicationContext。 10）
 * 遍历调用所有SpringApplicationRunListener的contextLoaded()方法。 11） 调用ApplicationContext的refresh()方法，完成IoC容器可用的最后一道工序。 12）
 * 查找当前ApplicationContext中是否注册有CommandLineRunner，如果有，则遍历执行它们。 13）
 * 正常情况下，遍历执行SpringApplicationRunListener的finished()方法、（如果整个过程出现异常，
 * 则依然调用所有SpringApplicationRunListener的finished()方法，只不过这种情况下会将异常信息一并传入处理）
 */
// @SpringBootApplication//组合注解 @Configuration、@EnableAutoConfiguration 、 @ComponentScan
// @SpringCloudApplication //组合注解 SpringBootApplication+EnableDiscoveryClient+EnableCircuitBreaker
// @EnableScheduling //计划任务
@Configuration // <beans>
@EnableAutoConfiguration(exclude = {AopAutoConfiguration.class, RedisAutoConfiguration.class})
@ComponentScan(basePackages = {"com.lsl.commons"}/*,excludeFilters={
                                                 @Filter(type = FilterType.REGEX,
                                                 pattern = {"com.lsl.commons.fegin"})}*/
)
@ImportRole(name = "Admin")
// @Import(DefaultImportSelector.class)
@Import(RoleImportBeanDefinitionRegistrar.class)
@EnableCache(packages = {"com.lsl.commons"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LslApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LslApplication.class, args);
        // context.close();
        // ConfigurableApplicationContext context = new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF)
        // .sources(StartUpApplication.class)
        // .run(args);
        // System.out.println(context.getBean(Role.class));
        // System.out.println(context.getBean(User.class));
        // System.out.println(context.getBeansOfType(User.class));
        // context.stop();
        // context.close();
    }
}