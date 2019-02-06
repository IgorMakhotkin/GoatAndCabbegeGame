package goatandcabbegegame.model;

import goatandcabbegegame.model.navigation.MiddlePosition;
import goatandcabbegegame.model.navigation.CellPosition;
import java.util.ArrayList;
import java.util.Random;

/*
 * GameField - абстракция поля, состоящего из ячеек;  
 * контейнер для игровых юнитов (козы, стен и травы)
 */

/**
 *
 * @author IgorHome
 */
public class GameField {

    // ------------------------------ Размеры ---------------------------
    /**
     * Поля содержащие высоту и ширину поля
     */
    public static int widthField;

    /**
     *
     */
    public static int heightField;

    /**
     *
     */
    public static int w;

    /**
     *
     */
    public static int h;
   /**
    * конструктор поля
    */
    GameField (){
       // Random randSize = new Random();
       // setSize(randSize.nextInt(20) + 8, randSize.nextInt(12) + 9);
        setSize(w,h);
           //setSize(10,7);
    }
/**
 * Функция устанавливающая размеры поля
 * @param width
 * @param height 
 */
    public final void setSize(int width, int height) {
        
        this.widthField = width;
        this.heightField = height;
        CellPosition.setHorizontalRange(1, width);
        CellPosition.setVerticalRange(1, height);
    }
/**
 * Функция возвращающая ширину 
 * @return ширина
 */
    public int width() {
        return this.widthField;
    }
/**
 * Функция возвращающая высоту
 * @return высота
 */
    public int height() {
        return this.heightField;
    }
	
    
    // ---------------------------- Коза ----------------------------
    /**
     * 
     */
    private Goat goat;
    /**
     * 
     * @return 
     */
    public Goat goat(){
       return this.goat;
    }
    /**
     * Функция устанавливающая козу на поле
     * @param pos
     * @param obj
     * @return успешность установки
     */
    public boolean setGoat(CellPosition pos, Goat obj){
        
        boolean isGoatSet = false;
        if( obj != null && pos != null){
            obj.setField(this);
            isGoatSet = obj.setPosition(pos);
            
            if ( isGoatSet ){               
                this.goat = obj;
                
            } else{
                this.goat = null;
                obj.setField(null);
            }
        }
        return isGoatSet;
    }
	
    // ---------------------------- Стены ----------------------------
    /**
     * Список стен
     */
    private ArrayList<WallPiece> walls = new ArrayList<WallPiece>();
    
    /**
     *
     * @return
     */
    public ArrayList<WallPiece> getWallPieces(){
        return walls;
    }
    /**
     * Функция проверяющая заната ли позиция стеной  
     * @param pos
     * @return успешность проверки
     */
    public boolean isWall(MiddlePosition pos){
        
        boolean isWall = false;
        for(int i= this.walls.size()-1; i>=0 && !isWall; --i){
            
            if( this.walls.get(i).position().equals(pos) ){
                isWall = true;
            }
        }

        return isWall;
    }
    /**
     * Функия добовляющая стены на поле
     * @param pos
     * @param obj
     * @return 
     */
    
    public boolean addWall(MiddlePosition pos, WallPiece obj){
        
        boolean wasSet = false;
        if(pos!=null && obj!=null ){
            obj.setField(this);
            wasSet = obj.setPosition(pos);
            
            if(wasSet == true){
                this.walls.add(obj);
            }
            else{
                obj.setField(null);
            }
        }

        return wasSet;
    }
    
    /**
     * Функция удаляющая стены
     * @param indexes 
     */
    public void removeWalls(ArrayList<CellPosition> indexes){
       for(int j = indexes.size()-1; j>=0; --j){
            for(int i = walls.size()-1; i>=0; --i){
                if(indexes.get(j).equals(walls.get(i).globalPosition())){
                    walls.remove(i);
                }
            }
       }
   }

    // ---------------------------- Ящики ----------------------------
    /**
     * Список ящиков
     */
    private ArrayList<Box> boxses = new ArrayList<Box>();
    
    /**
     *
     * @return
     */
    public ArrayList<Box> getBoxses(){
        return boxses;
    }
    /**
     * Функция проверяет выбраную позицию на соответствие списку ящиков
     * @param pos
     * @return 
     */
    public Box box(CellPosition pos){
        
        Box someBox = null;

        for(int i= this.boxses.size()-1; i>=0 && someBox==null; --i){
            
            if( this.boxses.get(i).position().equals(pos) ){
                someBox = this.boxses.get(i);
            }
        }
        
        return someBox;
    }
    /**
     * Функция проверяющая заната ли позиция
     * @param pos
     * @return усешность проверки
     */
    public boolean isBox(CellPosition pos){
        
        boolean isBox = false;
        for(int i= this.boxses.size()-1; i>=0 && !isBox; --i){
            
            if( this.boxses.get(i).position().equals(pos) ){
                isBox = true;
            }
        }

        return isBox;
    }
    /**
     * Функия добовляющая ящик на поле
     * @param pos
     * @param obj
     * @return 
     */
    public boolean addBox(CellPosition pos, Box obj){
        
        boolean wasSet = false;
        if(pos!=null && obj!=null ){
            obj.setField(this);
            wasSet = obj.setPosition(pos);
            
            if(wasSet == true){
                this.boxses.add(obj);
            }
            else{
                obj.setField(null);
            }
        }

        return wasSet;
    }
    /**
     * Функия удаляющая ящики
     * @param indexes 
     */
    public void removeBoxses(ArrayList<CellPosition> indexes){
       for(int j = indexes.size()-1; j>=0; --j){
            for(int i = boxses.size()-1; i>=0; --i){
                if(indexes.get(j).equals(boxses.get(i).globalPosition())){
                    boxses.remove(i);
                }
            }
       }
   }
    
   

    // ---------------------------- Трава ----------------------------
    /**
     * Список трав
     */
    private ArrayList<Grass> grasses = new ArrayList<Grass>();
    
    /**
     *
     * @return
     */
    public ArrayList<Grass> getGrasses(){
        return grasses;
    }
    
    /**
     * Функция проверяющая заната ли позиция
     * @param pos
     * @return успешность проверки
     */
     public boolean isGrass(CellPosition pos){
        
        boolean isGrass = false;
        for(int i= this.grasses.size()-1; i>=0 && !isGrass; --i){
            
            if( this.grasses.get(i).globalPosition().equals(pos) ){
                isGrass = true;
            }
        }

        return isGrass;
     }
     /**
      * Функция проверяет выбраную позицию на соответствие списку трав
      * @param pos
      * @return 
      */
    public Grass grass(CellPosition pos){
        
        Grass someGrass = null;

        for(int i= this.grasses.size()-1; i>=0 && someGrass==null; --i){
            
            if( this.grasses.get(i).globalPosition().equals(pos) ){
                someGrass = this.grasses.get(i);
            }
        }
        
        return someGrass;
    }
	
    /**
     * Функия добовляющая траву на поле
     * @param pos
     * @param obj
     * @return 
     */
    public boolean addGrass(CellPosition pos, Grass obj){
        
        boolean wasSet = false;
        if(pos!=null && obj!=null ){
            obj.setField(this);
            wasSet = obj.setPosition(pos);
            
            if(wasSet == true){
                this.grasses.add(obj);
                
            }
            else{
                obj.setField(null);
            }
        }

        return wasSet;
    }
    
    /**
     * Функия удаляющая траву
     * @param obj
     * @return 
     */
    public boolean removeGrass(Grass obj){
        
        for(int i= grasses.size()-1; i>=0; --i){
            if( grasses.get(i).equals(obj) ){
                return grasses.remove(i) != null;
            }
            
        }
        return false;
    }
    
    /**
     * Функция отчищающая список траву
     */
    public void distructGrasses(){
        this.grasses.clear();
    }
    
    /**
     * 
     * @param indexes 
     */
   public void removeGrass(ArrayList<CellPosition> indexes){
       for(int j = indexes.size()-1; j>=0; --j){
            for(int i = grasses.size()-1; i>=0; --i){
                if(indexes.get(j).equals(grasses.get(i).globalPosition())){
                    grasses.remove(i);
                }
            }
       }
   }
  
    // ---------------------------- Очистка ----------------------------
    
   /**
    * Функция отчиски всего поля
    */
    public void clear()
    {
        this.walls.clear();
        this.boxses.clear();
        this.grasses.clear();
        this.goat = null;
    }
}