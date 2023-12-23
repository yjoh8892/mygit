/**
 * Name: Yoo Jin Oh
 * Email: yoh56@wisc.edu
 * Team: JC
 * TA: Harper
 * Lecturer: Gary Dahl 
 * Notes to Grader: <optional extra notes>
 */

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	private LinkedList[] table;
    private int size;
    
    public HashTableMap() {
    	this(10);
    	}
    
    public HashTableMap(int capacity) {
    	table = new LinkedList[capacity];
        size = 0;
        
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
            }
        }
    
    @Override
    public boolean put(KeyType key, ValueType value) {
    	LinkedList list = table[Math.abs(key.hashCode()) % table.length];
    	
    	for (int i = 0; i < list.size(); i++) {
    		Element element = (Element) list.get(i);
    		
    		if (element.key.equals(key)) {
    			return false;
    			}
    		}
    	
    	list.add(new Element(key, value));
        size++;
        
        if ((double) size / table.length >= 0.8) {
        	growTable();
        }
        return true;
        }
    
    private void growTable() {
    	LinkedList[] oldTable = table;
        table = new LinkedList[table.length * 2];
        size = 0;
        
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }
        
        for (LinkedList list : oldTable) {
            for (int i = 0; i < list.size(); i++) {
                Element element = (Element) list.get(i);
                put(element.key, element.value);
                }
            }
        }
    
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        LinkedList list = table[Math.abs(key.hashCode()) % table.length];
        
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            
            if (element.key.equals(key)) {
            	return element.value;
                }
            }
        throw new NoSuchElementException();
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean containsKey(KeyType key) {
        LinkedList list = table[Math.abs(key.hashCode()) % table.length];
        
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);

            if (element.key.equals(key)) {
                return true;
                }
            }
        return false;
        }
    
    @Override
    public ValueType remove(KeyType key) {
        LinkedList list = table[Math.abs(key.hashCode()) % table.length];
        
        Element foundElement = null;
        
        for (int i = 0; i < list.size(); i++) {
        	Element element = (Element) list.get(i);
        	
        	if (element.key.equals(key)) {
                foundElement = element;
                break;
                }
        	}
        
        if (foundElement == null) {
            return null;
            }
        
        list.remove(foundElement);
        size--;
        return foundElement.value;
        }
    
    @Override
    public void clear() {
    	for (LinkedList list : table) {
            list.clear();
            }
    	size = 0;
    	}
    
    private class Element {
    	KeyType key;
        ValueType value;
        
        Element(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
            }
        }
    }
