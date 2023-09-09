package coding.piero.spring.beans.factory.support;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws BeansException;
}
