package goatandcabbegegame.model;

import goatandcabbegegame.model.events.GoatActionEvent;
import goatandcabbegegame.model.events.GoatActionListener;
import goatandcabbegegame.model.navigation.CellPosition;
import goatandcabbegegame.model.navigation.Direction;
import goatandcabbegegame.model.navigation.MiddlePosition;
import java.util.Random;
import javax.swing.JOptionPane;
/*
 * GameModel - абстракция всей игры; генерирует стартовую обстановку; 
 * следит за роботом с целью определения конца игры
 */

/**
 *
 * @author IgorHome
 */

public class GameModel {

    // ----------------------- Игровое поле и коза на нем ------------------
    /**
     * Поле содержит поле 
     */
    private  GameField _field;
    /**
     * Функция вовзращает поле
     * @return 
     */
    public GameField field(){
        
        return this._field;
    }
    /**
     * Поле содержит экземпляр класса Коза
     */
    private final Goat _goat = new Goat();
    
    /**
     *
     * @return
     */
    public Goat goat(){
        
        return this._goat;
    }
    
    {
        goat().addGoatActionListener(new GameModel.gameOverByAction() );
    }
    /**
     * Функия старта 
     */
    public void start(){
        generateField();
        
        // Вдруг игра завершилась, еще не начавшись
        identifyGameOver();
    }
    
    // -------------------- Целевая позиция козы --------------------------
    private CellPosition _targetPos;
    
    /**
     *
     * @return
     */
    public CellPosition targetPosition(){
        
        return this._targetPos;
    }
    
    // ------------ Задаем обстановку и следим за окончанием игры  ------------

    /**
     * Поля с позициями ящика и козы 
     */
private CellPosition _BoxPos;
private CellPosition _goatPos;
    /**
     * Функция генерации поля
     */
    private void generateField(){
       _field = new GameField();
        
        this._field.clear();
     
        
        
        int sizeMatr = GameField.heightField *GameField.widthField / 3;
        
        for (int k=1; k<=GameField.widthField;k++)
        {
            this._field.addWall( new MiddlePosition((new CellPosition(1,k)),Direction.north()), new WallPiece());
             this._field.addWall( new MiddlePosition((new CellPosition(GameField.heightField,k)),Direction.south()), new WallPiece());}
           for ( int j=1;j<=GameField.heightField;j++  )
           {
          this._field.addWall( new MiddlePosition((new CellPosition(j,1)),Direction.west()), new WallPiece());
         this._field.addWall( new MiddlePosition((new CellPosition(j,GameField.widthField)),Direction.east()), new WallPiece());
        }
        // Расставляем стены на поле
        for(int i= sizeMatr; i>=0; --i){
          this._field.addWall( randMPos() , new WallPiece());
           this._field.addGrass( randPos(), new blueGrass(10));
            this._field.addGrass( randPos(), new redGrass(5));
               _BoxPos=randPos();
             if(!field().isGrass(_BoxPos))
                this._field.addBox(_BoxPos, new Box());
        }
        
         
        
        do{    _targetPos = randPos();}
       while(!field().isBox(_targetPos)&&!field().isGrass(_targetPos));
               
        this._goat.setTargetPos(_targetPos);
        
        
         do{_goatPos=randPos();}
        while(!field().isBox(_goatPos)&&!field().isGrass(_goatPos));  
        this._field.setGoat( _goatPos, _goat);
        
        
       
    }
    
    /**
     * Функция возвращает рандомное значение для <b>CellPosition</b>
     * @return CellPosition
     */
    private CellPosition randPos()
    {
        Random val = new Random();
        return new CellPosition( val.nextInt(GameField.heightField) + 1 ,
                val.nextInt(GameField.widthField) + 1 );
    }
    
    /**
     * Функция возвращает рандомное значение для <b>MiddlePosition</b>
     * @return MiddlePosition
     */
    private MiddlePosition randMPos()
    {
        
        Direction []directions = { Direction.east(), Direction.north(),
                                    Direction.south(), Direction.west()};
        
        Random val = new Random();
        return new MiddlePosition( randPos() , directions[ val.nextInt(4) ] );
        
    }
    
    /**
     * Функция проверяет не наступил ли конец игры
     */
    private void identifyGameOver(){
        
        if(goat().globalPosition().equals(_targetPos))
        {
           
             JOptionPane.showMessageDialog(null, "Вы достигли цели!!!");
            this._field.distructGrasses();
        }
        else if( goat().amountОfСharge()==0 )
        {
            
             JOptionPane.showMessageDialog(null, "У вас закончились очки ходов");
            
               this._field.clear();
        }
    }
    
    /**
     * Функция выполняет проверку на "конец игры" при движение козы
     */
    private class gameOverByAction implements GoatActionListener{

        @Override
        public void goatMakedMove(GoatActionEvent e) {
            identifyGameOver();
        }
    }
    
}
