package goatandcabbegegame.model;

import goatandcabbegegame.model.navigation.Direction;
import goatandcabbegegame.model.navigation.MiddlePosition;

/**
 * Класс описывающий стену
 * @author IgorHome
 */
class WallPiece extends ObjectOnField
{
    // ----------------------- ѕозици¤ стены -------------------------
    private MiddlePosition mPos;
    public MiddlePosition position(){
        return this.mPos;
    }
    
    /**
     * Функция установки стены
     * @param pos
     * @return успешность установки
     */
    boolean setPosition(MiddlePosition pos){
        
        boolean res = false;
        
        if ( !field().isWall(pos) ){
            this.mPos = pos;
            this.pos = pos.cellPosition();
            res = true;
        }
        
        return res;
    }
    
    // ----------------------- Ориентация стены -------------------------
    
    /**
     * Поля с ориентацией стены
     */
    public  static final int VERTICAL = 1; 
    public  static final int HORIZONTAL = 2; 
    /**
     * Функция возвращает ориентацию стены
     * @return 
     */
    public int orientation()
    {
        Direction direct = position().direction();

        if(direct.equals(Direction.south()) || direct.equals(Direction.north()))   return VERTICAL;
        if(direct.equals(Direction.west()) || direct.equals(Direction.east()))     return HORIZONTAL;

        // TODO »сключение
        throw new IllegalStateException("Illegal orientation wall");
    }
    
    
}
