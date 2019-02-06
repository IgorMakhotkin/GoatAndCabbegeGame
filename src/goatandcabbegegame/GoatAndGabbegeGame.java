
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
import java.util.Scanner;
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
public class GoatAndGabbegeGame extends JFrame {

    private GameModel _model;
    
    private GameFieldPanel _gamePanel;

    private static final int FROM = 4;
    private static final int TO = 10;
    //===================================================================== main

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            // new ComboBox();
            
 
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер игрового поля(от 4 до 10): ");
        while (true) {
            System.out.print("Введите ширину: ");
            String line1 = sc.nextLine();
              System.out.print("Введите высоту: ");
               String line2 = sc.nextLine();
           
                GameField.w = Integer.valueOf(line1);
                GameField.h = Integer.valueOf(line2);
                if (isInRange(GameField.w, FROM, TO)&&isInRange(GameField.h, FROM, TO)) {
                    System.out.println("Правильный ввод. Запускаем игру");
                    break;
                } else {
                    System.out.printf("Необходимо ввести число от %d до %d. Еще раз...%n", FROM, TO);
                }
            
            
        }
    
                 new GoatAndGabbegeGame();
            
      
    
            } });}
    //============================================================== constructor

    /**
     *
     */
    public GoatAndGabbegeGame() {
        
        _model = new GameModel();
        _model.start();
        _gamePanel = new GameFieldPanel(_model);
   
 
      
        JPanel controlPanel = new JPanel(new FlowLayout());
   
        
JMenuBar menu = new JMenuBar(); 
JMenu fileMenu = new JMenu("Меню"); 

String fileItem1 = ("Новая игра");
String fileItem2 = ("Об Игре");
JMenuItem item1 = new JMenuItem(fileItem1); 
JMenuItem item2 = new JMenuItem(fileItem2); 
item1.setActionCommand(fileItem1.toLowerCase()); 
item1.addActionListener(new ActionNewGame()); 

item2.setActionCommand(fileItem2.toLowerCase()); 
item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
            
fileMenu.add(item1); 
fileMenu.add(item2); 
fileMenu.insertSeparator(1); 

          menu.add(fileMenu); 
          setJMenuBar(menu); 
        //... Create content pane with graphics area in center (so it expands)
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(controlPanel, BorderLayout.NORTH);
        content.add(_gamePanel, BorderLayout.CENTER);
        
        //... Set this window's characteristics.
        setContentPane(content);
        setTitle("Коза и капуста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        _gamePanel.setFocusable(true);
        _gamePanel.setVisible(true);      
      
 
    }
      private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                              
        new About(this).setVisible(true);
    }     

      private  static boolean isInRange(int value, int from, int to) {
        return value >= from && value <= to;
    }
 
   
    ////////////////////////////////////////////////////////////// ActionNewGame
    class ActionNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            
             if(GameField.w>0 && GameField.h>0 )
             {
                setVisible(false);
                 dispose();
                new GoatAndGabbegeGame();
              
            }
           
        }
    }  }  
     