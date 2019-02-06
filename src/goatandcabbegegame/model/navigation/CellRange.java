package goatandcabbegegame.model.navigation;

/*
 * Допустимый дипазон ячеек
 */

/**
 *
 * @author IgorHome
 */

public class CellRange
{
    // ------------------ Возможные значения ------------------
    private  int _min = 0;
    private  int _max = 0;

    /**
     *
     * @param min
     * @param max
     */
    public CellRange(int min, int max){
        if(min < 0)     min = 0;
        if(max < min)   max = min;
        
        _min = min;
        _max = max;
    }
    
    /**
     *
     * @return
     */
    public int min(){
        return _min;
    }

    /**
     *
     * @return
     */
    public int max(){
        return _max;
    }

    /**
     *
     * @return
     */
    public int length(){
        return _max - _min + 1;
    }
    
    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static boolean isValidRange(int min, int max) {
      return min > 0 && max >= min;  
    }

    // ------------------ Принадлежность диапазону ------------------

    /**
     *
     * @param val
     * @return
     */
    
    public boolean contains(int val){
       return val >= _min && val <= _max;
    }
}