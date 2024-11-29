package com.bank.accounts.web.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Gets the names of properties with null values in the provided object.
     *
     * @param source the object to inspect
     * @return an array of property names with null values
     * @throws IllegalArgumentException if the source is null
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        java.util.List<String> nullPropertyNames = new java.util.ArrayList<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                nullPropertyNames.add(pd.getName());
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }
}
