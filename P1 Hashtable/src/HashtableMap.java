import java.util.NoSuchElementException;

// class for Hashtable that provides user with all features of a Hashtable
// implements MapADT
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private int size; // stores amount of items in Hashtable
  private int capacity; // stores total space in Hashtable
  protected Hash<KeyType, ValueType>[] list; // stores all keys/vals

  /**
   * constructor for HashtableMap class size starts at 0 because Hashtable is always empty at first
   * 
   * @param capacity the capacity of the Hashtable array
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    size = 0;
    this.capacity = capacity;
    list = new Hash[capacity];
  }

  /**
   * constructor for HashtableMap class
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    size = 0;
    capacity = 8;
    list = new Hash[8]; // creates Hashtable array with capacity 8
  }

  @Override
  /**
   * insert method for Hashtable, uses helper method
   * 
   * @param key to be inserted
   * @param value to be inserted
   * @throws IllegalArgumentException if key is null or already exists
   */
  public void put(KeyType key, ValueType value) throws IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException("Key is null"); // if key is null
    }
    if (containsKey(key)) {
      throw new IllegalArgumentException("Key already exists"); // if key already existss
    }
    // checks to see if Hashtable needs to expand in capacity (if adding another Hash will exceed 70% capacity)
    if ((double) (size + 1) / (double) capacity >= 0.7) {
      this.doubleSize();
    }
    // inserts Hash using a helper method
    putIn(list, key, value);
  }

  @Override
  /**
   * checks if Hashtable contains a certain key
   * 
   * @param key to check for
   * @return true if key exists in Hashtable without being a Tombstone, false otherwise
   */
  public boolean containsKey(KeyType key) {
    // finds index based on key
    int index = Math.abs(key.hashCode()) % capacity;
    // linear probing
    while (list[index] != null) {
      if (list[index].getKey().equals(key)) {
        if (list[index].isTombstone()) {
          return false; // if key is a Tombstone
        }
        return true; // if key is found and not a Tombstone
      }
      index++;
      if (index == capacity) {
        index = 0; // loops back around if makes it to end of Hashtable
      }
    }
    return false; // if key can't be found
  }

  @Override
  /**
   * gets the value based on a specified key
   * 
   * @param key the key to extract value from
   * @throws NoSuchElementException if key doesn't exist in Hashtable
   * @return ValueType that corresponds with specified key
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    // finds hash key
    int index = Math.abs(key.hashCode()) % capacity;
    // linear probing
    while (list[index] != null) {
      if (list[index].getKey().equals(key)) {
        if (list[index].isTombstone()) {
          throw new NoSuchElementException("This key has been removed"); // if key has been previously removed
        }
        return list[index].getVal(); // returns value at specified key if it's found
      }
      // linear probing
      index++;
      if (index == capacity) {
        index = 0;
      }
    }
    throw new NoSuchElementException("This key does not exist"); // if key not found
  }

  @Override
  /**
   * removes a specified key from the Hashtable by setting it as a tombstone
   * 
   * @param key the key to be removed
   * @throws NoSuchElementException if key doesn't exist in Hashtable
   * @return ValueType the value of the key that was removed
   */
  public ValueType remove(KeyType key) throws NoSuchElementException {
    try {
      ValueType val = get(key); // to be returned later
      // finds hash key
      int index = Math.abs(key.hashCode()) % capacity;
      // linear probing
      while (list[index] != null) {
        if (list[index].getKey().equals(key)) {
          list[index].setTombstone(true); // sets removed Hash to a Tombstone
          size--; // size reduced by one
          return val; // value that was removed
        }
        index++;
        if (index == capacity) {
          index = 0;
        }
      }
      throw new NoSuchElementException("Key does not exist"); // if key can't be found
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Key does not exist"); // if key does not exist
    }

  }

  @Override
  // clears Hashtable
  public void clear() {
    for (int i = 0; i < capacity; i++) {
      list[i] = null;
    }

  }

  @Override
  // getter for size of Hashtable
  public int getSize() {
    return size;
  }

  @Override
  // getter for capacity of Hashtable
  public int getCapacity() {
    return capacity;
  }

  @SuppressWarnings("rawtypes")
  /**
   * doubles the capacity of the Hashtable
   * called when size exceeds 70% of Hashtable capacity
   */
  public void doubleSize() {
    Hash[] newList = new Hash[capacity * 2]; // creates new Hash[] with twice capacity
    int realSize = size; // stores size before rehash
    capacity = capacity * 2; // doubles capacity
    rehash(list, newList); // helper method
    size = realSize; // keeps size the same
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  /**
   * helper method for put() method
   * 
   * @param list the Hashtable to be added to
   * @param object key to be added
   * @param object2 corresponding value to be added
   */
  public void putIn(Hash[] list, Object object, Object object2) {
    int index = Math.abs(object.hashCode()) % capacity;
    // linear probing
    while (list[index] != null && !list[index].isTombstone()) {
      index++;
      if (index == capacity) {
        index = 0;
      }
    }
    list[index] = new Hash(object, object2); // creates new Hash using parameters
    list[index].setTombstone(false); // makes it not a Tombstone
    size++; // size increases by one
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  /**
   * helper method to rehash when load factor is reached
   * @param oldList Hashtable with old capacity
   * @param newList HashTable with new capacity
   */
  public void rehash(Hash[] oldList, Hash[] newList) {
    // adds all old Hashes into new Hashtable
    for (int i = 0; i < capacity / 2; i++) {
      if (oldList[i] != null) {
        if (!oldList[i].isTombstone()) {
          putIn(newList, oldList[i].getKey(), oldList[i].getVal());
        }
      }
    }
    list = newList; // sets Hashtable to new Hashtable with double capacity
  }
}
