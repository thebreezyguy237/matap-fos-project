/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import java.util.Set;

/**
 *
 * @author OLIVIA
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    // Define the default hash-table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;
    
    // Define the maximum hash-table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;
    
    // Current hash-table capacity. Capacity is a power of 2
    private int capacity;
    
    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    
    // Specify a load factor used in the hash table
    private float loadFactorThreshold;
    
    // The number of entries in the map
    //RBTree<MyMap.Entry<K, V>> table;

    /** Construct a map with the default capacity and load factor */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }
    
    /** Construct a map with the specified initial capacity and default load factor */
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }
    
    /** Construct a map with the specified initial capacity and load factor */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
       /* if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);
        
        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity]; */
    }

    @Override
    public void clear() {
        //size = 0;
        //removeEntries();
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V get(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(K key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<V> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
