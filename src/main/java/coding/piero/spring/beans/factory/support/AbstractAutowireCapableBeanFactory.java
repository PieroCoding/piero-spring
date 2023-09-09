package coding.piero.spring.beans.factory.support;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private CglibSubclassingInstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed");
        }

        addSingleton(beanName, bean);
        return bean;
    }

    private Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructorToUse = null;
        for (Constructor ctr : constructors) {
            if (args != null && ctr.getParameterTypes().length == args.length) {
                constructorToUse = ctr;
                break;
            }
        }

        return instantiationStrategy.instantiate(beanName, beanDefinition, constructorToUse, args);
    }



}
