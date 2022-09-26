package Dak.Dictionary;

/** interface for objects that provide the behavior of a dictionary */
public abstract class Dictionary {
    /** represents a single entry stored inside of the dictionary*/
    public class Entry {
        String word;
        String definition;
        private void load(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }
        public Entry(String word, String definition) {
            load(word,definition);
        }
        public Entry(String tab_entry) {
            String [] splt_entry = tab_entry.split("\t");
            load(splt_entry[0],splt_entry[1]);
        }
    }
    /** returns the entry for a given word */
    public abstract Entry getEntry(String word);

    /** returns the definition for a given word */ 
    public String definition(String word) {
        return getEntry(word).definition;
    }
}
