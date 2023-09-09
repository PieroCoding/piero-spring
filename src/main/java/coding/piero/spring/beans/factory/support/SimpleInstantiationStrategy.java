package coding.piero.spring.beans.factory.support;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();

        try {
            if (constructor != null) {
                return clazz.getConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.newInstance();
            }
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new BeansException("failed to instantiate [" + clazz.getName() + "]");
        }
    }
}
