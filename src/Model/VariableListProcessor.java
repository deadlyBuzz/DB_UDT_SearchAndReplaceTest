/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import com.googlecode.concurrenttrees.radix.*;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import java.util.*;

/**
 * This is the swingworker class that will take the string object that contains
 * the source code and builds the VariableList object from the Data.
 * @author Alan Curley
 */
public class VariableListProcessor extends SwingWorker<Void,Integer> {
    JTextArea source;
    Integer i = 0;
    public Map<String, VariableList> items;
    
    public VariableListProcessor(JTextArea source){
        this.source = source;
        //items =  new ConcurrentRadixTree<>(new DefaultCharArrayNodeFactory());// create the Variables tree        
        items = new LinkedHashMap<>();
        i = 0;
    }
    
    public void setSource(JTextArea source){
        this.source = source;
    }    

    // This is the method that will be called in the background to allow the thread to run.
    // We can update the progress property of the class to report back how it is working.
    // Called when the "Execute" command is given???
    @Override
    protected Void doInBackground() throws InterruptedException{        
        // it is here we set up the Loop to go through Source, line by line and translate the variables into something
        // more meaningful.
        // Clear the progress settings.
        
        Integer percProgress;   // The percentage completion through the system
        Integer stateMachine = 0;   // a State Machine to define what part of the build process we're in.
        String currentType = "";
        i = 0;
        //setProgress(0);
        
        // build an Arraylist of Strings that represent each line in the source code.
        ArrayList<String> sourceCodeList;
        sourceCodeList = new ArrayList();
        sourceCodeList.addAll(Arrays.asList(source.getText().split("\\n")));
        
        for (String S:sourceCodeList){
            // this is where the work will take place.
            switch(stateMachine){
                case 0: // Searching for UDT Start
                    if(S.matches("TYPE\\W*UDT\\W+(\\d+).*")){ // Is this the start of a UDT?                        
                        currentType = "UDT"+S.replaceAll("TYPE\\W*UDT\\W+(\\d+).*", "$1");
                        items.put(currentType,new VariableList(currentType));
                        stateMachine = 1; // Found a UDT
                    }                                        
                    break;
                case 1:// found a UDT, inside the UDT waiting for the end declare.
                    if(S.matches("END_TYPE")){                        
                        stateMachine = 0; // go back to the start looking for UDTs.
                    }
                    else if(S.toUpperCase().trim().matches("([A-Z_0-9]+)\\W*:\\W*([A-Z_0-9]+).*")){ // variable declaration.
                        String temp = S.toUpperCase().trim().replaceAll("([A-Z_0-9]+)\\W*:\\W*([A-Z_0-9]+).*", "$1 - $2");
                        //items.getValueForExactKey(currentType).addVariable(temp);
                        items.get(currentType).addVariable(temp);
                        System.out.println(temp);                        
                    }                        
                    else if(S.toUpperCase().trim().matches("END_STRUCT.*")) // Allow VariableList to close the variable.
                        items.get(currentType).addVariable("END_STRUCT");
                    break;                   
            }
            percProgress = ((++i)*100/source.getLineCount())+1;
            publish(percProgress);
        }
        return null;
        //throw new UnsupportedOperationException("<<<< VPL-doInBackground(): Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void done(){
        System.out.println("Complete. :-)");
        JOptionPane.showMessageDialog(null, "Complete", "Complete", JOptionPane.INFORMATION_MESSAGE);
        Set<String> keys = items.keySet();
        for(String K:keys){
            items.get(K).printVariableList();
        }
        //throw new UnsupportedOperationException("<<<< VPL-done(): Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
        // Can safely update the GUI from this method.
        protected void process(List<Integer> chunks) {
        // Here we receive the values that we publish().
        // They may come grouped in chunks.
        int mostRecentValue = chunks.get(chunks.size()-1);         
        setProgress(mostRecentValue);
    }

    
}
