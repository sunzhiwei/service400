package com.yixin.service400.util;

import java.io.Serializable;

/**
 * @author Fred
 *
 */
public class Condition implements Serializable {

    private static final long serialVersionUID = 9104106721299671325L;

    private String property;
    
    private String signal;
    
    private Object value;

    /**
     * @return the property
     */
    public String getProperty() {
        return this.property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return the signal
     */
    public String getSignal() {
        return this.signal;
    }

    /**
     * @param signal the signal to set
     */
    public void setSignal(String signal) {
        this.signal = signal;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
