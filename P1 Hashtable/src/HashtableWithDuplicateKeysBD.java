// --== CS400 Project One File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// placeholder class for HashtableWithDuplicateKeys object type
public class HashtableWithDuplicateKeysBD<KeyType, ValueType>
    implements MapADT<KeyType, List<ValueType>> {
  private int capacity; // capacity of keys in hashtable
  private int numValues; // amount of values in hashtable
  protected Hash<KeyType, List<ValueType>>[] list; // array of object type Hash that stores all keys
                                                   // and value combinations

  @SuppressWarnings("unchecked")
  // constructor, default capacity of 8
  public HashtableWithDuplicateKeysBD() {
    capacity = 8; // default capacity if capacity not specified
    numValues = 0; // starts off with 0 values
    list = new Hash[capacity];
  }

  @SuppressWarnings("unchecked")
  /**
   * constructor with specified capacity
   * 
   * @param capacity amount of keys hashtable can store
   */
  public HashtableWithDuplicateKeysBD(int capacity) {
    this.capacity = capacity; // if capacity is specified
    numValues = 0; // starts off with 0 values
    list = new Hash[capacity];
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  /**
   * put one individual combination into hashtable
   * 
   * @param key   to be stored into hashtable
   * @param value what the key in hashtable corresponds to
   */
  public void putOne(KeyType key, ValueType value) {
    list[numValues] = new Hash(key, value);
    numValues++;
  }

  public void removeOne(KeyType key, ValueType value) {

    numValues--;
  }

  public int getNumberOfValues() {
    return numValues;
  }

  @Override
  /**
   * checks if a key exists in hashtable
   * 
   * @return true if key exists in hashtable, false otherwise
   */
  public boolean containsKey(KeyType key) {
    // iterates through hashtable to see if key exists
    for (int i = 0; i < numValues; i++) {
      if (list[i] != null && list[i].getKey().equals(key)) {
        return true; // if key exists
      }
    }
    return false; // if key doesn't exist
  }

  @SuppressWarnings("unchecked")
  @Override
  /**
   * returns the List of values that corresponds with specified key
   * 
   * @key the key to be searched for
   * @throws NoSuchElementException if key doesn't exist in hashtable
   */
  public List<ValueType> get(KeyType key) throws NoSuchElementException {
    List<ValueType> postList = new ArrayList<ValueType>();
    if (containsKey(key)) {
      for (int i = 0; i < numValues; i++) {
        if (list[i].getKey().equals(key)) {
          postList.add((ValueType) list[i].getVal());
          return postList;
        }
      }
      throw new NoSuchElementException("key doesn't exist");
    } else {
      throw new NoSuchElementException("key doesn't exist");
    }
  }

  @Override
  public List<ValueType> remove(KeyType key) throws NoSuchElementException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public int getSize() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  // getter for hashtables capacity
  public int getCapacity() {
    // TODO Auto-generated method stub
    return capacity;
  }

  @Override
  public void put(KeyType key, List<ValueType> value) throws IllegalArgumentException {
    // TODO Auto-generated method stub

  }
}
