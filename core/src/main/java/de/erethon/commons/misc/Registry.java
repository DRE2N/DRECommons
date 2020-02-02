/*
 * Written in 2015-2020 by Daniel Saukel
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software
 * to the public domain worldwide.
 *
 * This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package de.erethon.commons.misc;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Represents a cache or registry of objects.
 *
 * @param <K> the key type
 * @param <E> the element type
 * @author Daniel Saukel
 */
public class Registry<K, E> implements Iterable<E> {

    protected BiMap<K, E> elements = HashBiMap.<K, E>create();

    /**
     * Returns the element registered under the given key.
     *
     * @param key the key to check
     * @return the element registered under the given key
     */
    public E get(K key) {
        return elements.get(key);
    }

    /**
     * Returns the first element that satisfies the given predicate.
     *
     * @param predicate the predicate to check
     * @return the first element that satisfies the given predicate
     */
    public E getFirstIf(Predicate<E> predicate) {
        for (E element : elements.values()) {
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    /**
     * Returns all elements that satisfy the given predicate.
     *
     * @param predicate the predicate to check
     * @return all elements that satisfy the given predicate
     */
    public Collection<E> getAllIf(Predicate<E> predicate) {
        Collection<E> checked = new ArrayList<>();
        for (E element : elements.values()) {
            if (predicate.test(element)) {
                checked.add(element);
            }
        }
        return checked;
    }

    /**
     * Returns true if the Registry contains the key, false if not.
     *
     * @param key the key
     * @return true if the Registry contains the key, false if not
     */
    public boolean containsKey(K key) {
        return elements.containsKey(key);
    }

    /**
     * Returns true if the Registry contains the element, false if not.
     *
     * @param element the element
     * @return true if the Registry contains the element, false if not
     */
    public boolean contains(E element) {
        return elements.containsValue(element);
    }

    /**
     * Adds an element to the Registry.
     *
     * @param key     the key of the element
     * @param element the element
     */
    public void add(K key, E element) {
        elements.put(key, element);
    }

    /**
     * Removes the element that matchs the given key from the Registry.
     *
     * @param key key of the element to remove
     */
    public void removeKey(K key) {
        elements.remove(key);
    }

    /**
     * Removes an element from the Registry.
     *
     * @param element the element to remove
     */
    public void remove(E element) {
        elements.values().remove(element);
    }

    /**
     * Returns a Stream of all elements.
     *
     * @return a Stream of all elements
     */
    public Stream<E> stream() {
        return elements.values().stream();
    }

    /**
     * Returns an Iterator of the elements.
     *
     * @return an Iterator of the elements
     */
    @Override
    public Iterator<E> iterator() {
        return elements.values().iterator();
    }

}
