/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatandcabbegegame;

/**
 *
 * @author IgorHome
 */
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author IgorHome
 */
public class About extends JDialog {

    /** Creates new form About
     * @param parent */
    public About(JFrame parent) {
        super(parent,true);
        initComponents();
        pack();
        Rectangle parentBounds = parent.getBounds();
        Dimension size = getSize();
        
        int x = Math.max(0, parentBounds.x + (parentBounds.width - size.width) / 2);
        int y = Math.max(0, parentBounds.y + (parentBounds.height - size.height) / 2);
        setLocation(new Point(x, y));
    }

 
    private void initComponents() {                          
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        copyrightTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Об игре Коза и капуста");
        mainPanel.setLayout(new java.awt.GridBagLayout());

        mainPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(11, 11, 12, 12)));
        copyrightTextArea.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        copyrightTextArea.setColumns(50);
        copyrightTextArea.setEditable(false);
        copyrightTextArea.setLineWrap(true);
        copyrightTextArea.setRows(15);
        copyrightTextArea.setText("Правила игры: Игроку необходимо довести козу до капусты за отведеное количество шагов.\n Подобрав красную траву игрок может перемещать два ящика одновременно. \n\n"
                + "Управление:\n 1)Что бы тянуть ящик на себя, зажмите клавишу Shift и выберете направление стрелочками.\n"
                + "2) Что бы толкать два ящика, зажмите клавишу Ctrl и  выберете направление стрелочками.\n"
                + "3) Что бы толкать и тянуть два ящика одновременно, зажмите клавишу Alt и выберете направление стрелочками.\n");
        copyrightTextArea.setWrapStyleWord(true);
        copyrightTextArea.setBorder(null);
        copyrightTextArea.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 24, 0);
        mainPanel.add(copyrightTextArea, gridBagConstraints);

        closeButton.setMnemonic('C');
        closeButton.setText("Закрыть");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        mainPanel.add(closeButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);

    }                        

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        setVisible(false);
        dispose();
    }                                           


              
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea copyrightTextArea;
    private javax.swing.JPanel mainPanel;
                

}
