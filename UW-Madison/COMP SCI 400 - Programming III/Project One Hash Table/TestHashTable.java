/**
 * Name: Yoo Jin Oh
 * Email: yoh56@wisc.edu
 * Team: JC
 * TA: Harper
 * Lecturer: Gary Dahl 
 * Notes to Grader: <optional extra notes>
 */

import java.util.NoSuchElementException;

public class TestHashTable {
	public static boolean testPutAndSize() {
        HashTableMap<Integer, Integer> numbers = new HashTableMap<>();
        
        for (int i = 1; i <= 5; i++) {
            if (!numbers.put(i, i)) {
            	return false;
            	}
            }
        
        for (int i = 1; i <= 5; i++) {
            if (numbers.put(i, i)) {
                return false;
                }
            }
        
        if (numbers.size() != 5) {
        	return false;
        	}
        
        for (int i = 6; i <= 10; i++) {
            if (!numbers.put(i, i)) {
                return false;
                }
            }
        
        if (numbers.size() != 10) {
        	return false;
        	}
        return true;
        }
	
	public static boolean testGet() {
        HashTableMap<Integer, Integer> numbers = new HashTableMap<>();
        
        for (int i = 1; i <= 5; i++) {
            if (!numbers.put(i, i)) {
                return false;
                }
            }
        
        for (int i = 0; i < 10; i++) {
            if (i >= 1 && i <= 5) {
                Integer value = numbers.get(i);
                
                if (value == null || value != i) {
                    return false;
                    }
                } else {
                	try {
                		numbers.get(i);
                		return false;
                		} catch (NoSuchElementException e) {
                			
                		} catch (Exception e) {
                			return false;
                			}
                	}
            }
        return true;
        }
	
	public static boolean testContainsKey() {
        HashTableMap<Integer, Integer> numbers = new HashTableMap<>();
        
        for (int i = 1; i <= 5; i++) {
            if (!numbers.put(i, i)) {
                return false;
                }
            }
        
        for (int i = 0; i < 10; i++) {
            if (i >= 1 && i <= 5) {
                if (!numbers.containsKey(i)) {
                    return false;
                    }
                } else if (numbers.containsKey(i)) {
                	return false;
                	}
            }
        return true;
        }
	
	public static boolean testRemove() {
        HashTableMap<Integer, Integer> numbers = new HashTableMap<>();
        
        for (int i = 1; i <= 5; i++) {
            if (!numbers.put(i, i)) {
                return false;
                }
            }
        
        int expectedSize = 5;

        for (int i = 0; i < 10; i++) {
            if (i >= 1 && i <= 5) {
                Integer value = numbers.remove(i);
                
                if (value == null || value != i) {
                    return false;
                    }
                expectedSize--;
                
                if (expectedSize != numbers.size()) {
                    return false;
                    }
                } else {
                	Integer value = numbers.remove(i);
                	if (value != null) {
                		return false;
                		}
                	}
            }
        return true;
        }
	
	public static boolean testClear() {
        HashTableMap<Integer, Integer> numbers = new HashTableMap<>();
        
        for (int i = 1; i <= 5; i++) {
            if (!numbers.put(i, i)) {
                return false;
                }
            }
        if (numbers.size() != 5) {
        	return false;
        	}
        
        numbers.clear();
        
        if (numbers.size() != 0) {
            return false;
            }
        
        for (int i = 1; i <= 5; i++) {
            if (!numbers.put(i, i)) {
                return false;
                }
            }
        return true;
        }
	
	public static void main(String[] args) {
		System.out.println("testPutAndSize(): " + testPutAndSize());
        System.out.println("testGet(): " + testGet());
        System.out.println("testContainsKey(): " + testContainsKey());
        System.out.println("testRemove(): " + testRemove());
        System.out.println("testClear(): " + testClear());
        }
	}
