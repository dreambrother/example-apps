package com.blogspot.nikcode.lazy.proxy;

import java.util.List;

/**
 *
 * @author nik
 */
public interface ListLoader<T> {
    
    List<T> load();
}
