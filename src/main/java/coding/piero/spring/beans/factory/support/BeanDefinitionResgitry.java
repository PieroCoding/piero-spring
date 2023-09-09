package coding.piero.spring.beans.factory.support;

import coding.piero.spring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionResgitry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
