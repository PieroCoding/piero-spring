package coding.piero.spring.test;

import static org.junit.Assert.assertTrue;

import coding.piero.spring.BeanDefinition;
import coding.piero.spring.BeanFactory;
import coding.piero.spring.test.bean.UserService;
import org.junit.Test;


public class ApiTest
{

    @Test
    public void shouldAnswerWithTrue()
    {
        //1.初始化BeanFactory
        BeanFactory beanFactory = new BeanFactory();

        //2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registreBeanDefinition("userService", beanDefinition);

        //3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
