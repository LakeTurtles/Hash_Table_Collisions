package org.example;

public class HashTable {

    private int INITIAL_SIZE = 16;
    private HashEntry[] data; // LinkedList

    class HashEntry {
        String key;
        String value;
        HashEntry next;

        HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashTable() {
        data = new HashEntry[INITIAL_SIZE];
    }

    public void put(String key, String value) {

        int index = getIndex(key);


        HashEntry entry = new HashEntry(key, value);


        if (data[index] == null) {
            data[index] = entry;
        }

        else {
            HashEntry entries = data[index];

            // Walk to the end...
            while(entries.next != null) {
                entries = entries.next;
            }
            // Add our new entry there
            entries.next = entry;
        }
    }

    public String get(String key) {


        int index = getIndex(key);

        // Get the current list of entries
        HashEntry entries = data[index];

        // While there are elements in the linked list...
        while (entries != null) {
            if (entries.key.equals(key))    // Check for match
                return entries.value;       // if match found return
            entries = entries.next;         // else go to next node in chain
        }

       return null;                         // return null if no match found
    }

    private int getIndex(String key) {
        // Get the hash code
        int hashCode = key.hashCode();

        // Convert to index
        int index = (hashCode & 0x7fffffff) % INITIAL_SIZE; // BitWise Operand to convert negatives to positive hashes
//        int index = hashCode % INITIAL_SIZE;

        // This is a 'Hack' to force collision for testing
        if (key.equals("John Smith") || key.equals("Sandra Dee") || key.equals("Tim Lee")) {
            index = 4;
        }

        return index;
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : data) {
            if(entry == null) {
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }
}
