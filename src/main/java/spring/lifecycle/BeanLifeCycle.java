package spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 参考：
 * https://github.com/upcAutoLang/SpringBeanLIfeCycleDemo
 * https://yanglei1992.github.io/2021/01/13/java/spring/java-spring-springBean%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F/#more
 */
public class BeanLifeCycle {

    public static void main(String[] args) {

        System.out.println("现在开始初始化容器");

        ApplicationContext context = new ClassPathXmlApplicationContext("beanLife.xml");
        System.out.println("容器初始化成功");
        //得到Preson，并使用
        Person person = context.getBean("person",Person.class);
        System.out.println(person);
        // 得到 Computer，并使用
        Computer computer = context.getBean("computer", Computer.class);
        System.out.println(computer);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)context).registerShutdownHook();
    }
}
