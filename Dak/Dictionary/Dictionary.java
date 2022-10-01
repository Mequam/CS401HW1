package Dak.Dictionary;

/** interface for objects that provide the behavior of a dictionary */
public abstract class Dictionary {
    /** represents a single entry stored inside of the dictionary*/
    public static class Entry implements Comparable<Entry>{
        public String word;
        public String definition;
        private void load(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }
        /** Loads an entry from a given word and a given definition */
        public Entry(String word, String definition) {
            load(word,definition);
        }
        /** 
         * loads an entry from a tab deliminated string where the definition
         * is to the right of the tab, and the word is to the left
        */
        public Entry(String tab_entry) {
            String [] splt_entry = tab_entry.split("\t");


            load(splt_entry[0],splt_entry[1]);
        }

        public int length() {
            return word.length() + definition.length();
        }

       /** Compares to entries
        * Note: null is less than ALL other values */ 
        @Override
        public int compareTo(Dictionary.Entry other) {
            if (word == null && other.word == null) {
                return 0;
            }
            if (word == null && other.word != null) {
                return -1;
            }
            if (word != null && other.word == null) {
                return 1;
            } 

            return word.compareTo(other.word);
        }

        @Override
        public String toString() {
            return word + "\t" + definition;
        }
    }

    /** returns the entry for a given word */
    public abstract Entry getEntry(String word);
    
    /** adds the given entry to the dictionary 
     * if that entry already exists, overide it
    */
    public abstract void addEntry(Entry entry);

    public void addEntry(String word,String def) {
        addEntry(new Entry(word,def));
    }

    /** returns the definition for a given word */ 
    public String definition(String word) {
        Entry e = getEntry(word);
        if (e != null)
            return e.definition;
        return null;
    }
}
