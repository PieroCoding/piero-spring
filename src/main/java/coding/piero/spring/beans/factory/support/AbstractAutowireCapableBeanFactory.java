package coding.piero.spring.beans.factory.support;

import coding.piero.spring.beans.BeansException;
import coding.piero.spring.beans.PropertyValue;
import coding.piero.spring.beans.factory.config.BeanDefinition;
import coding.piero.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            applyPropertyValues(beanName, bean, beanDefinition);
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

        return getInstantiationStrategy().instantiate(beanName, beanDefinition, constructorToUse, args);
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        try {
            PropertyValue[] propertyValueList = beanDefinition.getPropertyValues().getPropertyValues();
            for (PropertyValue ptv : propertyValueList) {
                String name = ptv.getName();
                Object value = ptv.getValue();

                if (value instanceof BeanReference) {
                    // A依赖B，获取B的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values:" + beanName);
        }
    }

    private void setFieldValue(Object bean, String propertyName, Object propertyValue) throws Exception {
        Class clazz = bean.getClass();
        Field field = clazz.getDeclaredField(propertyName);
        field.setAccessible(true);
        field.set(bean, propertyValue);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
