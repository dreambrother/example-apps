package com.blogspot.nikcode.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "obj", namespace = "com.blogspot.nikcode.types")
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjectWithNamespace {

    @XmlElement(namespace = "com.blogspot.nikcode.values")
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
