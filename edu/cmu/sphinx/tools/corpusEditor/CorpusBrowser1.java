package edu.cmu.sphinx.tools.corpusEditor;

import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Copyright 1999-2006 Carnegie Mellon University.
 * Portions Copyright 2002 Sun Microsystems, Inc.
 * Portions Copyright 2002 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 * <p/>
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 * <p/>
 * User: Peter Wolf
 * Date: Jan 30, 2006
 * Time: 1:38:01 PM
 */
public class CorpusBrowser1 implements TreeSelectionListener {
    private JTree corpusTree;
    private JTree wordTree;
    private JPanel mainPane;
    ConfigurationManager cm;
    Corpus corpus;
    private JScrollPane corpusScrollPane;
    private JScrollPane wordScrollPane;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Usage: CorpusBrowser1 propertiesFile corpusFile");
            System.exit(1);
        }

        String propertiesFile = args[0];
        String corpusFile = args[1];

        try {
            URL url = new File(propertiesFile).toURI().toURL();
            ConfigurationManager cm = new ConfigurationManager(url);

            Corpus corpus = CorpusBuilder.readCorpus(corpusFile);

            final CorpusBrowser1 cb = new CorpusBrowser1();
            JFrame f = new JFrame("CorpusBrowser1");
            cb.setCorpus(cm,corpus);

            f.setContentPane(cb.mainPane);
            f.pack();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            f.setVisible(true);



        } catch (MalformedURLException e) {
            throw new Error(e);
        } catch (PropertyException e) {
            throw new Error(e);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    private void setCorpus(ConfigurationManager cm, Corpus corpus) {
        this.cm = cm;
        this.corpus = corpus;

        DefaultMutableTreeNode cn = new DefaultMutableTreeNode("Corpus and stuff");

        for( Utterance u : corpus.utterances ) {
            DefaultMutableTreeNode un = new DefaultMutableTreeNode(u.getPcmFile() + " " + u.getBeginTime() + " " + u.getEndTime() + " " + u.getTranscript());
            for( Word w : u.getWords() ) {
                DefaultMutableTreeNode wn = new DefaultMutableTreeNode(w.spelling + " " + w.beginTime + " " + w.endTime);
                wn.setUserObject(w);
                un.add(wn);
            }
            cn.add(un);
        }

        corpusTree = new JTree( cn );

        corpusTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        corpusTree.addTreeSelectionListener(this);

        corpusScrollPane.setViewportView(corpusTree);

        DefaultMutableTreeNode wwn = new DefaultMutableTreeNode("Words and stuff");

        for( String s : corpus.getSpellings() ) {
            DefaultMutableTreeNode sn = new DefaultMutableTreeNode(s);
            for( Word w : corpus.getWords(s) ) {
                DefaultMutableTreeNode wn = new DefaultMutableTreeNode(w.spelling + " " + w.beginTime + " " + w.endTime);
                wn.setUserObject(w);
                sn.add(wn);
            }
            wwn.add(sn);
        }

        wordTree = new JTree( wwn );

        wordTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        wordTree.addTreeSelectionListener(this);

        wordScrollPane.setViewportView(wordTree);
    }

    /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode n =  (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        if( n.getUserObject() instanceof Word ) {
        Word word = (Word) n.getUserObject();

        System.out.println(word);


            final WordBrowser w = new WordBrowser();
            JFrame f = new JFrame("WordBrowser");
            w.spectrogram.setWord(cm,word);

            f.setContentPane(w.mainPane);
            f.setTitle( word.toString() );
            f.pack();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            f.setVisible(true);
        }

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     */
    private void $$$setupUI$$$() {
        mainPane = new JPanel();
        mainPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        wordScrollPane = new JScrollPane();
        mainPane.add(wordScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null));
        wordTree = new JTree();
        wordTree.setEditable(false);
        wordScrollPane.setViewportView(wordTree);
        corpusScrollPane = new JScrollPane();
        mainPane.add(corpusScrollPane, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null));
        corpusTree = new JTree();
        corpusScrollPane.setViewportView(corpusTree);
    }
}
