package goatandcabbegegame.model;

import goatandcabbegegame.model.events.GoatActionEvent;
import goatandcabbegegame.model.events.GoatActionListener;
import goatandcabbegegame.model.navigation.Direction;
import goatandcabbegegame.model.navigation.MiddlePosition;
import goatandcabbegegame.model.navigation.CellPosition;
import java.util.ArrayList;
import java.util.Random;


/**
 * Класс описывающий объект Коза со свойствами <b>currentBlueGrass</b> и <b>currentRedGrass</b>
 * Коза - может передвигаться по полю, если имеется очки передвижения; 
 * самостоятельно  определяет, куда может ходить; может использовать траву, 
 * находящиеся в поле.
 * 
 */
public class Goat extends ObjectOnField
{
    // ------------------- Коза "питается" от травы и может их менять -----------------
    /** Поле содержащие синию траву, дающую шаги*/
    private Grass currentBlueGrass = new Grass( (new Random()).nextInt(10)+5 );
    /**Поле содержащие красную траву, дающую силу */
     private Grass currentRedGrass = new Grass( 0 );
    /**
     * Функция съесть траву
     * @param grass 
     */
    public void useGrass(Grass grass){
        if ( grass != null && globalPosition().equals(grass.globalPosition()) ){
        
            if ( grass instanceof blueGrass ){
               int amountОfSteps=this.currentBlueGrass.amountОfСharge()+grass.amountОfСharge();
               Grass bufBlueGrass = new Grass(amountОfSteps);
          
            
            this.currentBlueGrass = bufBlueGrass;
        }
            else if( grass instanceof redGrass)
            {
                int amountOfPower=this.currentRedGrass.amountОfСharge()+grass.amountОfСharge();
                Grass bufRedGrass = new Grass(amountOfPower);
                this.currentRedGrass=bufRedGrass;
               
            }
            field().removeGrass(grass);
            
            fireGoatMovePerformed();
        }
    }
    
    private CellPosition _targPos;

    /**
     *
     * @param targPos
     */
    public final void setTargetPos( CellPosition targPos ){
        this._targPos = targPos;
    }
	/**
         * Функция получения значения поля{@link Product#((Grass)grass).amountОfСharge()}
         * @return количество заряда
         */
    public int amountОfСharge(){
        Object grass= this.currentBlueGrass;
      
        
        return ((Grass)grass).amountОfСharge();
    }
/**
 * Функция получения значения поля{@link Product#((Grass)grass).amountОfСharge()}
 * @return количество шагов
 */
      public int amountSteps(){
        Object grass= this.currentBlueGrass;
      
        
        return ((Grass)grass).amountОfСharge();
    }
      /**
       * Функция получения значения поля{@link Product#((Grass)grass).amountОfСharge()}
       * @return количество силы
       */
        public int amountPower(){
        Object grass= this.currentRedGrass;
      
        
        return ((Grass)grass).amountОfСharge();
    }
        
    /**
     *
     * @return
     */
    public Class typeOfValue(){
        return this.currentBlueGrass.getClass();
    }
  
  /**
   * Функция уменьшения числа шагов
   * @param delta 
   */
    
    protected void reduceCharge(int delta){
        this.currentBlueGrass.reduceCharge(delta);
        
    }
    /**
     * Функция уменьшения количества силы
     * @param beta 
     */
    protected void reduceCharge1(int beta){
      
        this.currentRedGrass.reduceCharge(beta);
    }
    
    // ------------------- Коза может перемещать ящики -----------------
   
    /**
     * Функция выполняющее толкание ящика от себя
     * @param direct 
     */
    public void pushABox(Direction direct){
        CellPosition cellPos1 = globalPosition().next(direct);
           
       MiddlePosition midPos1=new MiddlePosition( globalPosition().next(direct), direct);
     
         
       CellPosition newPos1 = globalPosition().next2(direct);
      
              Box box = this.field().box(cellPos1);
             
      if(  !field().isBox(newPos1) && !field().isWall(midPos1) )
      {
       box.setPosition(newPos1);
       setPosition(cellPos1);
      
             this.reduceCharge(this._delta);
             this.fireGoatMovePerformed();
    }
    
    }
    /**
     * Функция выполняющее толкание двух ящиков
     * @param direct 
     */
    public void pushATwoBox (Direction direct){
        CellPosition cellPos1 = globalPosition().next(direct);
        CellPosition cellPos2 = globalPosition().next2(direct);
          CellPosition newPos = globalPosition().next3(direct);
          MiddlePosition midPos1=new MiddlePosition( globalPosition().next(direct), direct);
          MiddlePosition midPos2=new MiddlePosition( globalPosition().next2(direct), direct);
           MiddlePosition midPos3=new MiddlePosition( globalPosition().next3(direct), direct.opposite());
          Box box1 = this.field().box(cellPos1);
          Box box2 = this.field().box(cellPos2);
          if(this.amountPower()>0)
          {
              if(!field().isWall(midPos1)&&!field().isWall(midPos2)&&!field().isWall(midPos3)&&!field().isBox(newPos))
              {
                  box2.setPosition(newPos);
                  box1.setPosition(cellPos2);
                 
                  setPosition(cellPos1);
                   this.reduceCharge1(this._beta);
                  this.fireGoatMovePerformed();
              }
          }
    }
    
    /**
     * Функция позволяющая тянуть ящик на себя
     * @param direct 
     */
      public void pullABox(Direction direct)
      {
          
      CellPosition cellPos = globalPosition().next(direct);
       MiddlePosition midPos=new MiddlePosition( globalPosition().invertNext(direct), direct.opposite());
             Box box = this.field().box(cellPos);
            
              CellPosition newPos1 = globalPosition().invertNext(direct);
              CellPosition newPos2 = globalPosition().invertNext2(direct);
              MiddlePosition midPos1=new MiddlePosition( globalPosition().invertNext(direct), direct);
      if(   !field().isBox(newPos2) && !field().isWall(midPos1)&& !field().isWall(midPos))
      {
       box.setPosition(newPos1);
      
      
             this.reduceCharge(this._delta);
             this.fireGoatMovePerformed();
      }
        }
      // Одновременно тянуть и толкать 2 ящика
      /**
       * Функция  позволяющая одновременно тянуть и толкать 2 ящика
       * @param direct 
       */
        public void pushAndPullABox(Direction direct)
        {
         CellPosition cellPos1 = globalPosition().next(direct);// Позиция первого ящика
         CellPosition cellPos2 = globalPosition().next(direct.opposite());// Позиция второго ящика
         Box box1 = this.field().box(cellPos1);
         Box box2 = this.field().box(cellPos2);
         CellPosition newPos1 = globalPosition().next2(direct);// Новая позиция первого ящика
         CellPosition newPos2 = globalPosition().invertNext(direct);// Новая позиция второго ящика
         CellPosition newPos3 = globalPosition().next2(direct);// Позиция для проверки наличия препятстсвия на пути ящиков в виде ящика 
           MiddlePosition midPos1=new MiddlePosition(globalPosition().next2(direct),direct.opposite());//Позиция для проверки наличия препятстсвия на пути ящиков в виде стены 
           MiddlePosition midPos2=new MiddlePosition(globalPosition().next(direct),direct);
           MiddlePosition midPos3=new MiddlePosition(globalPosition().next(direct),direct.opposite());//Позиция для проверки наличия препятстсвия на пути ящиков в виде стены
            if(this.amountPower()>0){
         if(field().isBox(cellPos1)&&field().isBox(cellPos2))
         {
            if(!field().isWall(midPos1)&&!field().isWall(midPos2)&&!field().isWall(midPos3)&&!field().isBox(newPos3))
            {
                // Устанавливаем новые позиции ящиков
                box1.setPosition(newPos1);
                box2.setPosition(newPos2);
                
                 this.reduceCharge1(this._beta);
                 this.fireGoatMovePerformed();
            }
            
         }
        }
        }
   
    // ------------------- Позиция Козы -----------------
    /**
     * Функиция устанавливающая позицию козы
     * @param pos
     * @return 
     */
    boolean setPosition(CellPosition pos){
        
        boolean success = false;
        if(pos!=null){
            this.pos = pos;
            success = true;
        }
        
        return success;
    }
	

    // ------------------- Двигаем козу -----------------
    /**Переменаая содержая количество шагов козы */
    private int _delta = 1;
    /** Переменаая содержая количество силы козы*/
    private int _beta = 1;
    /**
     * Фукнкия движения козы
     * @param direct 
     */
    public void makeMove(Direction direct){
        
        if ( moveIsPossible(direct) && !this._targPos.equals(globalPosition()) ){
            CellPosition newPos = globalPosition().next(direct);
            
            setPosition(newPos);
            
            reduceCharge(this._delta);
            
            fireGoatMovePerformed();
        } 
     
    }
    /**
     * Функия проверяющая возможность перемещения козы
     * @param direct
     * @return успешность перемещения
     */
    private boolean moveIsPossible(Direction direct){
        
        boolean success = false;
        
        if ( this.amountОfСharge() > 0 ){
            MiddlePosition middlelPos = new MiddlePosition( globalPosition(), direct);
         
            CellPosition cellPos = globalPosition().next(direct);
           Box box = field().box(cellPos);
            
            success = !field().isWall(middlelPos) &&( box==null)
                    && globalPosition().hasNext(direct);
            
           if(box!=null&&!field().isWall(middlelPos))
           {
                pushABox(direct);
           }
          
        }
        
        return success;
    }
    
    // ------------------- Слушатели -----------------
    /**
     * Список слушателей
     */
    private ArrayList<GoatActionListener> _listenerList = new ArrayList<GoatActionListener>(2);
 
    /**
     * Функия добовляющая в слушатели
     * @param RListener 
     */
    public void addGoatActionListener(GoatActionListener RListener){
        this._listenerList.add(RListener);
    }
    /**
     * Функия выполняет перемещение козы в результате наступления события
     */
    protected void fireGoatMovePerformed(){
        GoatActionEvent rEvent = new GoatActionEvent(this);
        
        for(GoatActionListener rListener : this._listenerList){
            rListener.goatMakedMove(rEvent);    
        }
    }
}