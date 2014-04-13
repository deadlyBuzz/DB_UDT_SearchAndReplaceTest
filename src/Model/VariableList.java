package Model;

import com.googlecode.concurrenttrees.radix.*;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;


/**
 * This Class represents the DB/UDT Blocks in the program.
 * When initialised with a source block, the class will trawl through the program 
 * and identify DBs, UDTs, etc. and identify the memory type used.
 * This will then be stored in a map
 * 
 * Going to try using a Radix tree from https://code.google.com/p/concurrent-trees/
 * (concurrent-trees-2.3.0.jar)
 * 
 * @author Alan Curley
 */

public class VariableList {
    public RadixTree<String> variables = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory()); // a Radixtree - Funky hashmap, to hold all the variables and their associated types


    /**
     * Should be similar to the same as the one used in SourceEntry.resolveMemory();
     * Maybe if I can combine both to a single item using an import or library?
     * @param lineEntry a String containing the variable to be used.
     * @return the new String that represents the new memory type for substitutions.
     */
    public static String resolveMemory(String lineEntry){
        return "";
    }
}
