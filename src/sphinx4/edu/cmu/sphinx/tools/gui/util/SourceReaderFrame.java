/*
 * SourceReaderFrame.java
 *
 * Created on February 14, 2007, 4:42 PM
 * 
 * Portions Copyright 2007 Mitsubishi Electric Research Laboratories.
 * Portions Copyright 2007 Harvard Extension Schoool, Harvard University
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL 
 * WARRANTIES.
 */

package edu.cmu.sphinx.tools.gui.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author  Ariani
 */

public class SourceReaderFrame extends javax.swing.JFrame {
    
    private String _text = new String(); 
    
    /** Creates new form SourceReaderFrame */
    public SourceReaderFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jDialog = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jDialog.setModal(true);
        jTextArea.setMargin(new java.awt.Insets(20, 20, 20, 20));
        jScrollPane1.setViewportView(jTextArea);

        jDialog.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        getContentPane().add(jButton1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//               // this part is used to test the system properties and display them all
//                Properties props = System.getProperties();
//                for (Enumeration e = props.keys(); e.hasMoreElements();) {
//                    String param = (String) e.nextElement();
//                    String value = props.getProperty(param);
//                    System.out.println("["+param+"]="+value);
//                }
//                System.out.println("### path value is "+ props.getProperty("java.class.path"));

                try {

                    String classname = new String("C:/project/source/JavaApplication2/sphinx4-1.0beta/edu/cmu/sphinx/tools/gui/guibuild.xml");   
                    BufferedReader br = new BufferedReader(new FileReader(classname));
                         System.out.println("File found");
                    String thisline;
                    while ( (thisline = br.readLine()) != null ){
                        _text = _text.concat(thisline+'\n');
                    }
                    br.close();
                }catch(IOException e){
                    System.err.println("IO error :"+e.getMessage());
                }
                jTextArea.setText(_text);
                jDialog.setSize(800,500);
                jDialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SourceReaderFrame().setVisible(true);
            }            
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables
    
}
