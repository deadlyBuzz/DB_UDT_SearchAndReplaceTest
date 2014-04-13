/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


import javax.swing.*;

/**
 * This is the swingworker class that will take the string object that contains
 * the source code and builds the VariableList object from the Data.
 * @author Alan Curley
 */
public class VariableListProcessor extends SwingWorker<Void,Void> {
    JTextArea source;
    
    
    VariableListProcessor(JTextArea source){
        this.source = source;
    }
    
    public void setSource(JTextArea source){
        this.source = source;
    }    

    // This is the method that will be called in the background to allow the thread to run.
    // We can update the progress property of the class to report back how it is working.
    // Called when the "Execute" command is given???
    @Override
    protected Void doInBackground() throws Exception {        
        // it is here we set up the Loop to go through Source, line by line and translate the variables into something
        // more meaningful.
        source.getLineCount();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void done(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
