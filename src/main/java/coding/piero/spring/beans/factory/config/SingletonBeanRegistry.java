package coding.piero.spring.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
