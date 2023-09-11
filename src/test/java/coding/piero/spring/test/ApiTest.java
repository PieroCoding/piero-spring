package coding.piero.spring.test;

import static org.junit.Assert.assertTrue;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.PropertyValue;
import coding.piero.spring.beans.PropertyValues;
import coding.piero.spring.beans.factory.config.BeanDefinition;
import coding.piero.spring.beans.factory.config.BeanReference;
import coding.piero.spring.beans.factory.support.DefaultListableBeanFactory;
import coding.piero.spring.beans.bean.UserDao;
import coding.piero.spring.beans.bean.UserService;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;


public class ApiTest {

    @Test
    public void testBeanFactory() throws BeansException {
        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2.构建bean定义
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addProperty(new PropertyValue("uId", "10001"));
        propertyValues.addProperty(new PropertyValue("userDao", new BeanReference("userDao")));
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        BeanDefinition userDaoBeanDefinition = new BeanDefinition(UserDao.class);

        //3.注册bean
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);
        beanFactory.registerBeanDefinition("userDao", userDaoBeanDefinition);

        //4.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", null);
        userService.queryUserInfo();

        //5.第二次获取bean
        UserService singletonUserService = (UserService) beanFactory.getBean("userService");
        singletonUserService.queryUserInfo();

    }

    @Test
    public void testParameterTypes() throws Exception {
        Class beanClass = UserService.class;
        Constructor[] constructors = beanClass.getDeclaredConstructors();
        Constructor ctr = constructors[0];
        Class[] parameterTypeClazz = ctr.getParameterTypes();

        Constructor<UserService> userServiceConstructor = beanClass.getDeclaredConstructor(parameterTypeClazz);
        UserService userService = userServiceConstructor.newInstance(new Object[0]);
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

        Object bean = enhancer.create();
        System.out.println(bean);
    }
}
