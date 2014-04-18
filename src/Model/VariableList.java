package Model;

//import com.googlecode.concurrenttrees.radix.*;
//import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import java.util.*;


/**
 * This Class represents the DB/UDT Blocks in the program.
 * When initialised with a source block, the class will trawl through the program 
 * and identify DBs, UDTs, etc. and identify the memory type used.
 * This will then be stored in a map
 * 
 * Going to try using a Radix tree from https://code.google.com/p/concurrent-trees/
 * (concurrent-trees-2.3.0.jar)
 * (Well, I was but LinkedHashMap can be iterated through)
 * 
 * @author Alan Curley
 */

public class VariableList {
    //public RadixTree<String> variables;// = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory()); // a Radixtree - Funky hashmap, to hold all the variables and their associated types
    Map<String,String> varList;         // A map of the variables associated with this 
    Map<String,VariableList> subList;
    public ArrayList<String> varPrefix; // When handling child elements, each parent element should be maintained.
    public String name;


    /**
     * Should be similar to the same as the one used in SourceEntry.resolveMemory();
     * Maybe if I can combine both to a single item using an import or library?
     * @param lineEntry a String containing the variable to be used.
     * @return the new String that represents the new memory type for substitutions.
     */
    public static String resolveMemory(String lineEntry){
        return "";
    }
    
    public VariableList(){
        //variables = new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());// create the Variables tree        
        varList = new LinkedHashMap<>();
        varPrefix = new ArrayList<>();
    }
    
    public VariableList(String name){
        varList = new LinkedHashMap<>();
        varPrefix = new ArrayList<>();
        this.name = name;
    }
    
    /**
     * This is to be updated to utilise "resolveMemory" when building the tree.
     * in the meantime, assign everything to "byte"
     * @param variable 
     */
    public void addVariable(String variable){
        //variables.put(variable, "byte");    
        String subString = "";

        if(variable.matches(".+ - STRUCT.*")) // if this is a Structure Tag.
                varPrefix.add(variable.replaceAll("(.+) - STRUCT.*", "$1"));
        else if(variable.matches("END_STRUCT"))
                varPrefix.remove(varPrefix.size()-1); // remove the last prefix.
        
        if(varPrefix.size()>0)
            subString = varPrefix.toString().replaceAll(" ,", ".");
        varList.put(subString+"."+variable, "byte");
    }
    
    public void printVariableList(){
        try{
            Set<String> keys = varList.keySet();
            for(String K:keys)
                System.out.println(name+"."+varList.get(K)+"."+K);
        }catch(NullPointerException NPE){
            System.err.println("<<<< printVariableList oops: Line"+String.valueOf(varList.size()));
        }
        
    }
    
}
