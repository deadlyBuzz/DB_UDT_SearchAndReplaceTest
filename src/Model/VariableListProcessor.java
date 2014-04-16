/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

/**
 * This is the swingworker class that will take the string object that contains
 * the source code and builds the VariableList object from the Data.
 * @author Alan Curley
 */
public class VariableListProcessor extends SwingWorker<Void,Integer> {
    JTextArea source;
    Integer i = 0;
    
    public VariableListProcessor(JTextArea source){
        this.source = source;
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
        
        Integer percProgress;    
        i = 0;
        //setProgress(0);
        
        // build an Arraylist of Strings that represent each line in the source code.
        ArrayList<String> sourceCodeList;
        sourceCodeList = new ArrayList();
        sourceCodeList.addAll(Arrays.asList(source.getText().split("\\n")));
        
        for (String S:sourceCodeList){
            if(S.matches("TYPE\\W*UDT\\W+\\d+.*"))
                System.out.println(S);
            percProgress = ((++i)*100/source.getLineCount());
            publish(percProgress);
            /*if(!(oldPercProgress.equals(percProgress)))
            {
                try{ ///
                Thread.sleep(100);
                /*
                }catch(InterruptedException ignore){}
                oldPercProgress = percProgress;                
            } */               
//            if(i.equals(269))
//                System.out.println(String.valueOf(i)+"\t"+S);
        }
        return null;
        //throw new UnsupportedOperationException("<<<< VPL-doInBackground(): Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void done(){
        System.out.println("Complete. :-)");
        JOptionPane.showMessageDialog(null, "Complete", "Complete", JOptionPane.INFORMATION_MESSAGE);
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
