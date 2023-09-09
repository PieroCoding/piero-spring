package coding.piero.spring.test;

import static org.junit.Assert.assertTrue;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.factory.config.BeanDefinition;
import coding.piero.spring.beans.factory.BeanFactory;
import coding.piero.spring.beans.factory.support.DefaultListableBeanFactory;
import coding.piero.spring.test.bean.UserService;
import org.junit.Test;


public class ApiTest {

    @Test
    public void testBeanFactory() throws BeansException {
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        //4.第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService");
        singletonUserService.queryUserInfo();

    }
}
