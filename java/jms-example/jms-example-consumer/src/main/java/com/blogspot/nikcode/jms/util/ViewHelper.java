package com.blogspot.nikcode.jms.util;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nik
 */
public final class ViewHelper {

    private ViewHelper() { }
    
    public static String getValuesAsString() {
        return StringUtils.join(InMemoryStorage.getValues(), "<br/>");
    }
}
