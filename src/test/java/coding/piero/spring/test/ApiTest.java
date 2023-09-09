package coding.piero.spring.test;

import static org.junit.Assert.assertTrue;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.factory.config.BeanDefinition;
import coding.piero.spring.beans.factory.BeanFactory;
import coding.piero.spring.beans.factory.support.DefaultListableBeanFactory;
import coding.piero.spring.test.bean.UserService;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;


public class ApiTest {

    @Test
    public void testBeanFactory() throws BeansException {
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        //3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "piero");
        userService.queryUserInfo();

        //4.第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService", "piero");
        singletonUserService.queryUserInfo();

    }

    @Test
    public void testParameterTypes() throws Exception {
        Class beanClass = UserService.class;
        Constructor[] constructors = beanClass.getDeclaredConstructors();
        Constructor ctr = constructors[0];
        Class[] parameterTypeClazz = ctr.getParameterTypes();

        Constructor<UserService> userServiceConstructor = beanClass.getDeclaredConstructor(parameterTypeClazz);
        UserService userService = userServiceConstructor.newInstance("piero");
        System.out.println(userService);
    }

    @Test
    public void testEnhancer() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        Object bean = enhancer.create(new Class[]{String.class}, new Object[]{"piero"});
        System.out.println(bean);
    }
}
