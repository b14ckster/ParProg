package ru.spbstu.telematics.java;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.SetValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.collections4.multiset.HashMultiSet;

import java.util.*;

import static java.lang.Math.abs;


public class MyHashSetValuedHashMap<K, V>
       implements SetValuedMap<K, V>
{
    
    private HashMap<K, HashSet<V>> myH;
    private int size;

    public MyHashSetValuedHashMap(int mapC) {
        this.myH = new HashMap<>(mapC);
        size = 0;
    }

    public MyHashSetValuedHashMap(){
        this(16);
    }
    




    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean containsKey(Object o) {
        return myH.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        for (Map.Entry<K, HashSet<V>> pair : myH.entrySet())
            if (pair.getValue().contains(o)) return true;
        return false;
    }

    @Override
    public boolean containsMapping(Object o, Object o1) {
        if (myH.containsKey(o))
            return myH.get(o).contains(o1);
        return false;
    }

    @Override
    public Set<V> get(K key) {
        return myH.get(key);
    }

    @Override
    public boolean put( K key, V value) {
        if (!myH.containsKey(key)) {
            myH.put(key, new HashSet<V>());
            size++;
        }
        myH.get(key).add(value);
        return true;
    }

    @Override
    public boolean putAll(K key, Iterable<? extends V> iterable) {
        if (iterable != null){
            for (V v: iterable)
                put(key, v);
            return true;
        }
        return false;
    }

    @Override
    public boolean putAll(Map<? extends K, ? extends V> map) {
        if (map!=null && map.size()!=0) {
            for (Map.Entry<? extends K, ? extends V> pair : map.entrySet())
                put(pair.getKey(), pair.getValue());
            return true;
        }
        return false;
    }

    @Override
    public boolean putAll(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        if (multiValuedMap!=null&&multiValuedMap.size()>0){
            for (Map.Entry<? extends K, ? extends V> e:multiValuedMap.entries())
                put(e.getKey(), e.getValue());
            return true;
        }
        return false;
    }

    @Override
    public Set<V> remove(Object o) {
        if (myH.containsKey(o)) {
            return myH.remove(o);
        }
        return null;
    }

    @Override
    public boolean removeMapping(Object o, Object o1) {
        if (myH.containsKey(o)) {
            if (myH.get(o).contains(o1)&&myH.get(o).size()==1){
                myH.remove(o);
                return true;
            }
            else return myH.get(o).remove(o1);
        }
        return false;
    }

    @Override
    public void clear() {
        myH.clear();
        size =0;
    }

    @Override
    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> res = new ArrayList<>();
        for (Map.Entry<K, HashSet<V>> pair : myH.entrySet()) {
            for (V v : pair.getValue())
                res.add(new Entry(pair.getKey(), v));
        }
        return res;
    }

    @Override
    public MultiSet<K> keys() {
        MultiSet <K> res = new HashMultiSet<>();
        for (Map.Entry<K, HashSet<V>> pair : myH.entrySet())
            for (int i = 0; i< pair.getValue().size(); i++)
                res.add(pair.getKey());

        return res;
    }

    @Override
    public Set<K> keySet() {
        return myH.keySet();
    }

    @Override
    public Collection<V> values() {
        Collection <V> res = new ArrayList<>();
        for (Map.Entry<K, HashSet<V>> pair : myH.entrySet())
            for (V v: pair.getValue())
                res.add(v);
        return res;
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> res = new HashMap<>();
        for (Map.Entry<K, HashSet<V>> pair : myH.entrySet()) {
            Collection <V> collection = new ArrayList<>();
                for( V v: pair.getValue())
                    collection.add(v);
            res.put(pair.getKey(), collection);
        }
        return res;
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        return new MyIterator<>();
    }

    static class Entry<K, V> implements Map.Entry<K,V>{

        private final K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final K getKey() { return key; }

        public V getValue() { return value; }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    class MyIterator<K, V> implements MapIterator <K,V> {
        //HashMap<K, V> hashMap;
        int index;
        int indexInSet;
        K keys[];
        ArrayList<V> values;
        Entry<K, V> current;

        public MyIterator(){//ArrayList<Entry<K, V>>[] t) {
            keys = (K[]) myH.keySet().toArray();
            //values = (V[]) myH.
            //table = t;
            index = 0;
            indexInSet = -1;
            current = null;
        }

        @Override
        public boolean hasNext() {
            int i = index+1;
            while (i < keys.length&& myH.get(keys[i])== null)
                    i++;
            return i < keys.length;
        }

        @Override
        public K next() {
            Entry<K, V> next;
            if (!hasNext())
                return null;
            //prev = table[index].get(indexInArrayList);
            if ((indexInSet + 1) < myH.get(keys[index]).size()) {
                next =  new Entry<K, V>(keys[index], (V) myH.get(keys[index]).toArray()[indexInSet+1]);
                indexInSet++;
                current = next;
                return next.getKey();
            }
            indexInSet = 0;
            index++;
            current = new Entry<K,V >(keys[index], (V) myH.get(keys[index]).toArray()[indexInSet]);
            return (K) myH.get(keys[index]).toArray()[indexInSet];
        }

        @Override
        public K getKey() {
            return keys[index];
        }

        @Override
        public V getValue() {
            return (V) myH.get(keys[index]).toArray()[indexInSet];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

}
