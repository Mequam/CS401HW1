package Dak.Dictionary;

public class DictionaryLLTest {
    public static void main(String [] args) {
        DictionaryLL d = new DictionaryLL("Dictionary.txt");
        
        System.out.println(d.definition("about"));
        System.out.println(d.definition("code"));
        System.out.println(d.definition("computer"));
    }
}
