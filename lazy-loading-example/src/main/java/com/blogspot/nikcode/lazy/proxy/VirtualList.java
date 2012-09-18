package com.blogspot.nikcode.lazy.proxy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;

/**
 *
 * @author nik
 */
public class VirtualList<T> implements List<T> {
    
    private static final Logger LOG = Logger.getLogger(VirtualList.class);
    
    private List<T> lazyList;
    private ListLoader<T> loader;
    
    public VirtualList(ListLoader<T> loader) {
        this.loader = loader;
    }
    
    private List<T> getSource() {
        LOG.debug("Try to get list...");
        if (lazyList == null) {
            LOG.debug("Field is empty. Loading...");
            lazyList = loader.load();
        }
        return lazyList;
    }

    public int size() {
        return getSource().size();
    }

    public boolean isEmpty() {
        return getSource().isEmpty();
    }

    public boolean contains(Object o) {
        return getSource().contains(o);
    }

    public Iterator<T> iterator() {
        return getSource().iterator();
    }

    public Object[] toArray() {
        return getSource().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return getSource().toArray(a);
    }

    public boolean add(T e) {
        return getSource().add(e);
    }

    public boolean remove(Object o) {
        return getSource().remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return getSource().containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return getSource().addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return getSource().addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return getSource().removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return getSource().retainAll(c);
    }

    public void clear() {
        getSource().clear();
    }

    public T get(int index) {
        return getSource().get(index);
    }

    public T set(int index, T element) {
        return getSource().set(index, element);
    }

    public void add(int index, T element) {
        getSource().add(index, element);
    }

    public T remove(int index) {
        return getSource().remove(index);
    }

    public int indexOf(Object o) {
        return getSource().indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return getSource().lastIndexOf(o);
    }

    public ListIterator<T> listIterator() {
        return getSource().listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return getSource().listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return getSource().subList(fromIndex, toIndex);
    }
}
