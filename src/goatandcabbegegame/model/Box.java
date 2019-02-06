package goatandcabbegegame.model;

import goatandcabbegegame.model.navigation.CellPosition;
import goatandcabbegegame.model.navigation.Direction;
import goatandcabbegegame.model.navigation.MiddlePosition;

/**
 * Класс описывающие ящик (объект на поле)
 * @author IgorHome
 */
public class Box extends ObjectOnField{

    // ----------------------- Позиция двери -------------------------
    /**
     * поле позиции
     */
    private CellPosition Pos;
    /**
     * Конструктор
     * @return 
     */
    public CellPosition position(){
        return this.Pos;
    }
/**
 * Функия проверки позиции
 * @param pos
 * @return успешность установки позиции
 */
    boolean setPosition(CellPosition pos){
        
        boolean res = false;
        
        if ( field().box(pos) == null){
            this.Pos = pos;
          //  this.pos = pos.cellPosition();
            res = true;
        }
        
        return res;
    }
    
   

    boolean returnStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
      
}
