package coding.piero.spring.beans.factory;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.factory.config.BeanDefinition;

public interface BeanFactory {

    Object getBean(String beanName, Object... args) throws BeansException;
}
