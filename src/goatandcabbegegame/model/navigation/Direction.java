package goatandcabbegegame.model.navigation;

/*
 * Direction - абстракци¤ направлени¤ в системе координат "север-юг-восток-запад"; 
 * позвол¤ет сравнивать направлени¤ и порождать новые направлени¤ относительно 
 * текущего
 */

/**
 *
 * @author IgorHome
 */

public class Direction {
    
    /**
     * Определяем направление как угол в градусах от 0 до 360
     */
    private int _angle = 90;

    private Direction(int angle) {
        /**
         * Приводим заданный угол к допустимому диапазону 
         */
        angle = angle%360;
        if(angle < 0)    angle += 360;
        
        this._angle = angle;
    }
    
    /**
     * Возможные направления
     * @return 
     */
    
    public static Direction north()
    { return new Direction(90); }
    
    /**
     *
     * @return
     */
    public static Direction south()
    { return new Direction(270); }

    /**
     *
     * @return
     */
    public static Direction east()
    { return new Direction(0); }

    /**
     *
     * @return
     */
    public static Direction west()
    { return new Direction(180); }
    
  
  
     /**
      * Новые направления
      * @return 
      */
    @Override
    public Direction clone(){ 
        return new Direction(this._angle); 
    }
  
    /**
     *
     * @return
     */
    public Direction clockwise() { 
        return new Direction(this._angle-90); 
    }
    
    /**
     *
     * @return
     */
    public Direction anticlockwise() { 
        return new Direction(this._angle+90); 
    }
    
    /**
     *
     * @return
     */
    public Direction opposite() { 
        return new Direction(this._angle+180); 
    }
    
    /**
     *
     * @return
     */
    public Direction rightword()  { 
        return clockwise(); 
    }
    
    /**
     *
     * @return
     */
    public Direction leftword()  { 
        return anticlockwise(); 
    }
    
    /**
     * Cравнить направления
     * @param other
     * @return 
     */
    
    @Override
    public boolean equals(Object other) {

        if(other instanceof Direction) {
            // Типы совместимы, можно провести преобразование
            Direction otherDirect = (Direction)other;
            // Возвращаем результат сравнени¤ углов
            return  _angle == otherDirect._angle;
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return this._angle;
    }
    
    /**
     *
     * @param other
     * @return
     */
    public boolean isOpposite(Direction other) {
        return this.opposite().equals(other);
    }
}