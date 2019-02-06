/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatandcabbegegame;

import goatandcabbegegame.model.GameField;
import goatandcabbegegame.model.GameModel;
import goatandcabbegegame.view.GameFieldPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author IgorHome
 */
public class ComboBox extends JFrame {

    /**
     *
     */
    public ComboBox() {
               setTitle("Коза и капуста");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Font font = new Font("Verdana", Font.PLAIN, 18); 
 
        String[] items = {
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            
        };
 
        Container content = getContentPane();
 
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
         
        final JLabel label = new JLabel("Введите размер игрового поля ");
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(font);
        content.add(label);
        
         final JLabel label2 = new JLabel("Ширина:");
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(font);
     
         final JLabel label3 = new JLabel("Высота:");
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.setFont(font);
   
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box1 = (JComboBox)e.getSource();
                  String item1 = (String)box1.getSelectedItem();
                    GameField.w = Integer.parseInt(item1);
                    
                    
                JComboBox box2 = (JComboBox)e.getSource();  
                         String item2 = (String)box2.getSelectedItem();
                          GameField.h = Integer.parseInt(item2);
                       // label.setText(item);
                       System.out.println(GameField.w);
                         System.out.println(GameField.h);
            }
        };
        JButton bntStartGame = new JButton("Начать игру");
        bntStartGame.addActionListener(actionListener);
      
        JComboBox comboBoxWidthField = new JComboBox(items);
        comboBoxWidthField.setFont(font);
       comboBoxWidthField.setAlignmentX(CENTER_ALIGNMENT);
        comboBoxWidthField.addActionListener(actionListener);
    
 
        JComboBox comboBoxHeightField = new JComboBox(items); 
        comboBoxHeightField.setAlignmentX(CENTER_ALIGNMENT);
        comboBoxHeightField.setFont(font);
        comboBoxHeightField.addActionListener(actionListener);
         content.add(label2);
        content.add(comboBoxWidthField);
        content.add(label3);
        content.add(comboBoxHeightField);
       content.add(bntStartGame,BorderLayout.CENTER);
     setSize(450, 180);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
         
} 
    }
    

