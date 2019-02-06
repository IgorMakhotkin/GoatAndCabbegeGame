package goatandcabbegegame.view;

import goatandcabbegegame.model.Grass;
import goatandcabbegegame.model.Box;
import goatandcabbegegame.model.GameModel;
import goatandcabbegegame.model.Goat;
import goatandcabbegegame.model.blueGrass;
import goatandcabbegegame.model.events.GoatActionEvent;
import goatandcabbegegame.model.events.GoatActionListener;
import goatandcabbegegame.model.navigation.CellPosition;
import goatandcabbegegame.model.navigation.Direction;
import goatandcabbegegame.model.navigation.MiddlePosition;
import goatandcabbegegame.model.redGrass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JPanel;

/**
 *
 * @author IgorHome
 */
public class GameFieldPanel extends JPanel implements KeyListener {

    // ------------------------------ Модель игры ------------------------------
    private GameModel _model;

    // ------------------------------ Размеры ---------------------------------
    private static final int CELL_SIZE = 100;
    private static final int GAP = 0;
    private static final int FONT_HEIGHT = 30;

    // ------------------------- Цветовое оформление ---------------------------
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color GRID_COLOR = Color.BLACK;

    /**
     *
     */
    public int DirectGoat;

    /**
     *
     * @param model
     */
    public GameFieldPanel(GameModel model) {
        _model = model;
      
       // displayPanel();
        // Инициализация графики
        int width = 2 * GAP + CELL_SIZE * _model.field().width();
        int height = 2 * GAP + CELL_SIZE * _model.field().height();
        setPreferredSize(new Dimension(width, height+5));
        setBackground(Color.RED);

        _model.goat().addGoatActionListener(new GameFieldPanel.RepaintByAction());
        addKeyListener(this);
    }

   
    /**
     * Рисуем поле
     */
    @Override
    public void paintComponent(Graphics g) {

        // Отрисовка фона
        int width = getWidth();
        int height = getHeight();
//        int row ;
//        int col ;

        g.setColor(BACKGROUND_COLOR);
   
          
     try {
            BufferedImage img = ImageIO.read(new File("src\\land.png"));
                    g.drawImage(img, 0, 0, this);
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
          
     
    
      //g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);   // восстнанваливаем цвет пера

        // Отрисовка сетки
        drawGrid(g);

        // Отрисовка козы
        Point lefTop = leftTopCell(_model.goat().globalPosition());
   
   
     drawGoat(g, lefTop,DirectGoat);
     DirectGoat=1;
   


     
        // Отрисовка остальных юнитов, стен и ящиков
        CellPosition pos = new CellPosition(1, 1);
        Direction direct = Direction.east();
        boolean isPostLastColumn;
        do {
            boolean isPostLastRow;
            do {
                // Отрисовка травы
                Grass grass = _model.field().grass(pos);
                if(grass != null && grass instanceof redGrass)
                {
                    lefTop = leftTopCell(pos);
                    drawRedGrass(g,  lefTop);
                }
                else if (grass !=null && grass instanceof blueGrass)
            {
                    lefTop = leftTopCell(pos);
                    drawBlueGrass(g,  lefTop);
                }
                Box box = _model.field().box(pos);
                        if (box != null) // Отрисовка ящика
                        {
                            lefTop = leftTopCell(pos);
                            drawBox(g, lefTop);
                        }
                // Отрисовка стен 
                Direction d = Direction.north();
                for (int n = 1; n <= 4; n++) {
                    d = d.clockwise();
                    MiddlePosition mpos = new MiddlePosition(pos, d);

                    if (_model.field().isWall(mpos)) // Отрисовка стены
                    {
                        lefTop = leftTopCell(mpos);
                        drawWall(g, lefTop, mpos.direction());
                    } 
                       
                    
                }

                isPostLastRow = !pos.hasNext(direct);
                if (!isPostLastRow) {
                    pos = pos.next(direct);
                }
            } while (!isPostLastRow);

            direct = direct.opposite();

            isPostLastColumn = !pos.hasNext(Direction.south());
            if (!isPostLastColumn) {
                pos = pos.next(Direction.south());
            }
        } while (!isPostLastColumn);

        // Отрисовка целевой позиции
        lefTop = leftTopCell(_model.targetPosition());
        drawTargetPosition(g, lefTop);
    }


    private void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(GRID_COLOR);

        for (int i = 1; i <= _model.field().width() + 1; i++) // вертикальные линии
        {
            int x = GAP + CELL_SIZE * (i - 1);
            g.drawLine(x, 0, x, height);
        }

        for (int i = 1; i <= _model.field().height() + 1; i++) // горизотнальные линии
        {
            int y = GAP + CELL_SIZE * (i - 1);
            g.drawLine(0, y, width, y);
        }

    }

    private void drawGoat(Graphics g, Point lefTop, int DirectGoat) {
     
        setLayout(new BorderLayout());
        JButton display = new JButton("");
		display.setEnabled(false);
		add(display, BorderLayout.SOUTH);
                display.setText("");
				
	display.setText("Количество силы: " + _model.goat().amountPower() + "   Количество шагов: " + _model.goat().amountSteps());
        



    try {
          BufferedImage imgback = ImageIO.read(new File("src\\goat.png"));
           
            BufferedImage imgN = ImageIO.read(new File("src\\goatNorth.png"));
            BufferedImage imgS = ImageIO.read(new File("src\\goatSouth.png"));
            BufferedImage imgL = ImageIO.read(new File("src\\goatLeft.png"));
            BufferedImage imgR = ImageIO.read(new File("src\\goatRight.png"));
            if(DirectGoat==90)
            {
                g.drawImage(imgN, lefTop.x, lefTop.y, this);
            }
             if(DirectGoat==270)
            {
                g.drawImage(imgS, lefTop.x, lefTop.y, this);
            }
              if(DirectGoat==180)
            {
                g.drawImage(imgL, lefTop.x, lefTop.y, this);
            }
               if(DirectGoat==0)
            {
                g.drawImage(imgR, lefTop.x, lefTop.y, this);
            }
                 if(DirectGoat==1)   
                 {
                      g.drawImage(imgback, lefTop.x, lefTop.y, this);
                 }
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

     
   
    }
    
 

 private void drawRedGrass(Graphics g, Point lefTop) {

 try {
            BufferedImage img = ImageIO.read(new File("src\\red.png"));
                    g.drawImage(img, lefTop.x, lefTop.y, this);
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
  private void drawBlueGrass(Graphics g, Point lefTop) {

 try {
            BufferedImage img = ImageIO.read(new File("src\\blue.png"));
                    g.drawImage(img, lefTop.x, lefTop.y, this);
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }

    private void drawWall(Graphics g, Point lefTop, Direction direct) {
     
try {
       BufferedImage imgwall = ImageIO.read(new File("src\\wall1.jpg"));
                     if (direct.equals(Direction.west()) || direct.equals(Direction.east())) {
            g.drawImage(imgwall, lefTop.x-5, lefTop.y, this);}
            else
            {
                   BufferedImage imgwall1 = ImageIO.read(new File("src\\wall2.jpg"));
                   g.drawImage(imgwall1, lefTop.x, lefTop.y-5, this);
                    }
        
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }



 
    private void drawTargetPosition(Graphics g, Point lefTop) {
     
        try {
            BufferedImage imgback = ImageIO.read(new File("src\\gabbege.png"));
                    g.drawImage(imgback, lefTop.x, lefTop.y, this);
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void drawBox(Graphics g, Point lefTop) {
      try {
            BufferedImage img = ImageIO.read(new File("src\\box1.png"));
                    g.drawImage(img, lefTop.x, lefTop.y, this);
        } catch (IOException ex) {
            Logger.getLogger(GameFieldPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }

    private Point leftTopCell(CellPosition pos) {

        int left = GAP + CELL_SIZE * (pos.column() - 1);
        int top = GAP + CELL_SIZE * (pos.row() - 1);

        return new Point(left, top);
    }

    private Point leftTopCell(MiddlePosition mpos) {

        Point p = leftTopCell(mpos.cellPosition());

        if (mpos.direction().equals(Direction.south())) {
            p.y += CELL_SIZE;
            //p.x += CELL_SIZE;
        } else if (mpos.direction().equals(Direction.east())) {
            p.x += CELL_SIZE;
        }

        return p;
    }



    @Override
    public void keyPressed(KeyEvent ke) {
       
        if (ke.isAltDown()) {
            if (ke.getKeyCode() == KeyEvent.VK_UP) {         // толкать ящик вверх
               _model.goat().pushAndPullABox(Direction.north());
            } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {  //толкать ящик вниз
              _model.goat().pushAndPullABox(Direction.south());
            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {  // толкать ящик влево
               _model.goat().pushAndPullABox(Direction.west());
            } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) { // толкать ящик вправо
              _model.goat().pushAndPullABox(Direction.east());
            }
        }
              if (ke.isShiftDown()) {
            if (ke.getKeyCode() == KeyEvent.VK_UP) {         // тянуть ящик вверх
               _model.goat().pullABox(Direction.south());
            } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {  // тянуть ящик вниз
              _model.goat().pullABox(Direction.north());
            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {  // тянуть ящик влево
                _model.goat().pullABox(Direction.east());
            } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) { //тянуть ящик вправо
              _model.goat().pullABox(Direction.west());
            }
              }
               if (ke.isControlDown()) {
            if (ke.getKeyCode() == KeyEvent.VK_UP) {         // тянуть ящик вверх
               _model.goat().pushATwoBox(Direction.north());
                DirectGoat=90;
            } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {  // тянуть ящик вниз
              _model.goat().pushATwoBox(Direction.south());
               DirectGoat=270;
            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {  // тянуть ящик влево
                _model.goat().pushATwoBox(Direction.west());
                 DirectGoat=180;
            } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) { //тянуть ящик вправо
              _model.goat().pushATwoBox(Direction.east());
               DirectGoat=0;
            }
              }
            else {
            if (ke.getKeyCode() == KeyEvent.VK_UP) {         // перемещаемся вверх
                _model.goat().makeMove(Direction.north());
             
           DirectGoat=90;
        
            } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                _model.goat().makeMove(Direction.south());
           
               DirectGoat=270;
             
            } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {  // перемещаемся влево
                _model.goat().makeMove(Direction.west());
              
               DirectGoat=180;
           
            } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                _model.goat().makeMove(Direction.east()); 
               
               DirectGoat=0;
            
            }
           
             else if (ke.getKeyCode() == KeyEvent.VK_SPACE) { // берем траву
                Object grass = _model.field().grass(_model.goat().globalPosition());//
                if (grass != null)//
                {
                    _model.goat().useGrass((Grass) grass);
                }
            }

        }
           
              }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    private class RepaintByAction implements GoatActionListener {

        @Override
        public void goatMakedMove(GoatActionEvent e) {
            
     
            repaint();
          
        }
    }
}
