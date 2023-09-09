package coding.piero.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory
{
    private Map<String, BeanDefinition> definitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return definitionMap.get(name).getBean();
    }

    public void registreBeanDefinition(String name, BeanDefinition beanDefinition) {
        definitionMap.put(name, beanDefinition);
    }
}
