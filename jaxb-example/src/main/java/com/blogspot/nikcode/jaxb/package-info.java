@XmlSchema(xmlns = {
                @XmlNs(namespaceURI = "com.blogspot.nikcode.types", prefix = "type"),
                @XmlNs(namespaceURI = "com.blogspot.nikcode.values", prefix = "value")
        }, elementFormDefault = XmlNsForm.QUALIFIED)

package com.blogspot.nikcode.jaxb;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;