// class for object type Hash
public class Hash<KeyType, ValueType> {
  private KeyType key; // stores key value for individual hash
  private ValueType val; // stores value that key gives
  private boolean isTombstone; // tracks if Hash is a Tombstone or not

  /**
   * constructor 
   * is not a Tombstone by default
   * @param key what this Hash key will be set to
   * @param val what this Hash val will be set to
   */
  public Hash(KeyType key, ValueType val) {
    this.setKey(key);
    this.setVal(val);
    isTombstone = false;
  }
  // getter for key
  public KeyType getKey() {
    return key;
  }
  // setter for key
  public void setKey(KeyType key) {
    this.key = key;
  }
  // getter for val
  public ValueType getVal() {
    return val;
  }

  // setter for val
  public void setVal(ValueType val) {
    this.val = val;
  }
  // getter for isTombstone
  public boolean isTombstone() {
    return isTombstone;
  }
  // setter for isTombstone
  public void setTombstone(boolean isTombstone) {
    this.isTombstone = isTombstone;
  }

}
