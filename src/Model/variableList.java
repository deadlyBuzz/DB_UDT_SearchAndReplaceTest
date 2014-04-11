/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class variableList implements Runnable {
    RadixTree<String> variables = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());
    
    public variableList(){
        
    }
    
    public void processSourceCode(String sourceCode){
        
    }

    // Required method for Runnable.
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }            
    
}
