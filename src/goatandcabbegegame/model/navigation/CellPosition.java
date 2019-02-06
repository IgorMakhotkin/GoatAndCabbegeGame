package goatandcabbegegame.model.navigation;

import java.util.HashMap;
/**
 * Позиция ячейки 
 * @author IgorHome
 */

public class CellPosition
{
   
/**
 *  Диапазоны возможных значений по горизонтали и вертикали для всех позиций --
 */
    private static CellRange _horizontalRange = new CellRange(0, 10);
    private static CellRange _verticalRange = new CellRange(0, 10);

    /**
     *
     * @param min
     * @param max
     */
    public static void setHorizontalRange(int min, int max){
        if(CellRange.isValidRange(min, max))
        { _horizontalRange = new CellRange(min, max); }
    }
    
    /**
     *
     * @return
     */
    public static CellRange horizontalRange(){
      return _horizontalRange;
    }

    /**
     *
     * @param min
     * @param max
     */
    public static void setVerticalRange(int min, int max){
        if(CellRange.isValidRange(min, max))
        { _verticalRange = new CellRange(min, max); }
    }
    
    /**
     *
     * @return
     */
    public static CellRange verticalRange(){
        return _verticalRange;
    }
    
    // ------------------ Позиция внутри диапазона ---------------------
/**
 * Поля индексов строки и столбца
 */
    private int _row;
    private int _column;
    
    /**
     *
     * @param row
     * @param col
     */
    public CellPosition(int row, int col)
    {
        if(!isValid(row, col))
        {  //  TODO породить исключение 
        }

        _row = row;
        _column = col;
    }
    
    /**
     * Функция возвращает индекс строки
     * @return 
     */
    public int row(){
        
        if(!isValid())
        {  //  TODO породить исключение 
        }

        return _row;
    }

    /**
     * Функция возвращает индекс столбца
     * @return 
     */
    public int column(){

        if(!isValid())
        {  //  TODO породить исключение 
        }

        return _column;
    }
    
    
    /**
     *  Позиция может стать невалидной, если изменились диапазоны допустимых значений
     * @return 
     */
    public boolean isValid(){
        return isValid(_row, _column);
    }
    
    /**
     *
     * @param row
     * @param col
     * @return
     */
    public static boolean isValid(int row, int col){
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
   }
    
    @Override
    public CellPosition clone(){
        return new CellPosition(_row, _column);
    }
    
    // ------------------ Порождение и проверка смежных позиций ---------------
    
    /**
     * проверка смежных позиций
     * @param direct
     * @return 
     */
    public CellPosition next(Direction direct){
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }
    
    /**
     * проверка следующей смежной позиции
     * @param direct
     * @return 
     */
     public CellPosition next2(Direction direct){
        
        int[] newPos = calcNewPosition2(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    } 
     /**
      * проверка третьей смежной позиции
      * @param direct
      * @return 
      */
      public CellPosition next3(Direction direct){
        
        int[] newPos = calcNewPosition3(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }
     
      /**
       * 
       * @param direct
       * @return 
       */
     public CellPosition invertNext(Direction direct){
        
        int[] newPos = invertCalcNewPosition(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }
     /**
      * 
      * @param direct
      * @return 
      */
     public CellPosition invertNext2(Direction direct){
        
        int[] newPos = invertCalcNewPosition2(_row, _column, direct);
        return new CellPosition(newPos[0], newPos[1]);
    }
     /**
      * 
      * @param direct
      * @return 
      */
    public boolean hasNext(Direction direct){
        
        int[] newPos = calcNewPosition(_row, _column, direct);
        return isValid(newPos[0], newPos[1]);
    }
    
    /**
     * Вовзвращает массив из двух элементов: индекс строки, индекс столбца
     * @param row
     * @param col
     * @param direct
     * @return 
     */
 
    private int[] calcNewPosition(int row, int col, Direction direct){
        
        // Таблица смещения для различных направлений: (горизонталь,вертикаль)
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();
        
        offset.put(Direction.north(),   new int []{ 0, -1} );
        offset.put(Direction.south(),   new int []{ 0,  1} );
        offset.put(Direction.east(),    new int []{ 1,  0} );
        offset.put(Direction.west(),    new int []{-1,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
    /**
     * Вовзвращает массив из двух элементов: индекс строки, индекс столбца
     * @param row
     * @param col
     * @param direct
     * @return 
     */
     private int[] calcNewPosition2(int row, int col, Direction direct){
        
        /**
         * Таблица смещения для различных направлений: (горизонталь,вертикаль)
         */
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();
        
        offset.put(Direction.north(),   new int []{ 0, -2} );
        offset.put(Direction.south(),   new int []{ 0,  2} );
        offset.put(Direction.east(),    new int []{ 2,  0} );
        offset.put(Direction.west(),    new int []{-2,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
     /**
     * Вовзвращает массив из двух элементов: индекс строки, индекс столбца
     * @param row
     * @param col
     * @param direct
     * @return 
     */
          private int[] calcNewPosition3(int row, int col, Direction direct){
        
        
        /**
         * Таблица смещения для различных направлений: (горизонталь,вертикаль)
         */
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();
        
        offset.put(Direction.north(),   new int []{ 0, -3} );
        offset.put(Direction.south(),   new int []{ 0,  3} );
        offset.put(Direction.east(),    new int []{ 3,  0} );
        offset.put(Direction.west(),    new int []{-3,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
        private int[] invertCalcNewPosition2(int row, int col, Direction direct){
        
        
        /**
         * Таблица смещения для различных направлений: (горизонталь,вертикаль)
         */
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();
        
       offset.put(Direction.north(),   new int []{ 0, 1} );
        offset.put(Direction.south(),   new int []{ 0,  -1} );
        offset.put(Direction.east(),    new int []{ -1,  0} );
        offset.put(Direction.west(),    new int []{1,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
        private int[] invertCalcNewPosition(int row, int col, Direction direct){
        
      
        /**
         * Таблица смещения для различных направлений: (горизонталь,вертикаль)
         */
        HashMap<Direction, int [] > offset=  new  HashMap<Direction, int [] >();
        
        offset.put(Direction.north(),   new int []{ 0, 0} );
        offset.put(Direction.south(),   new int []{ 0,  0} );
        offset.put(Direction.east(),    new int []{ 0,  0} );
        offset.put(Direction.west(),    new int []{0,  0} );
        
        int[] newPos = new int[2];
        
        newPos[0] = _row + offset.get(direct)[1];
        newPos[1] = _column + offset.get(direct)[0];
        
        return newPos;
    }
    // ------------------ Сравнение позиций ---------------------
    
        /**
         * Сравнение позиций 
         * @param other
         * @return 
         */
    @Override
    public boolean equals(Object other){
        
        if(!isValid())
        {  //  TODO породить исключение 
        }

        if(other instanceof CellPosition) {
            // Типы совместимы, можно провести преобразование
            CellPosition otherPosition = (CellPosition)other;
            // Возвращаем результат сравнения углов
            return _row == otherPosition._row && _column == otherPosition._column;
        }
        
        return false;
    }
}
