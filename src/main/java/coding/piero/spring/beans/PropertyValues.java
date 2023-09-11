package coding.piero.spring.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addProperty(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getProperty(String name) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }

        return null;
    }


}
