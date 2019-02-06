/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatandcabbegegame.model;

import goatandcabbegegame.model.navigation.CellPosition;

/**
 *Класс описывающий траву
 * @author Igor
 */
public class Grass extends ObjectOnField{
    
    // ---------------- После уничтожения травы, доступ к ней запрещен -----------------
    /**
     * Поле содержащее свойство травы
     */
    private boolean _isDestroy = false;     // трава подлежит использованию
    
  /**
   * Функия уничтожения травы
   */
    public void destroy(){
	this._amountОfСharge = 0;
	this._isDestroy = true;
    }
    
    // ----------------------- Емкость и заряд травы -------------------------
         /**
          * емкость в условных единицах 
          */
    
    private int _amountОfСharge;    // заряд в условных единицах  

     /**
      * Конструктор для травы
      * @param amount 
      */
    public Grass( int amount) {
        // TODO исключение, если заряд больше емкости
  
        this._amountОfСharge = amount;
    }

    /**
     * Функиция возвращает количество заряда
     * @return 
     */
    public int amountОfСharge(){
        // TODO исключение, если трава уничтожена
        exceptionIfBatIsDestroy();
        return this._amountОfСharge;
    }
    
    /**
     *
     * @return
     */
    public boolean isAmountOfCharge() {
        return amountОfСharge() > 0;
    }
	
    /**
     *
     * @param delta
     */
    public void reduceCharge(int delta){
        // TODO исключение, если трава уничтожена
        exceptionIfBatIsDestroy();

        // TODO исключение, если delta не положительное
        if ( delta < 0 ){
            throw new IllegalStateException("delta is negative");
        }
        
	this._amountОfСharge -= delta;
	if(this._amountОfСharge < 0) 	this._amountОfСharge = 0;
    }
	
    
    // ----------------------- Позиция травы -------------------------

    /**
     * Позиция травы
     * @param pos
     * @return 
     */
    boolean setPosition(CellPosition pos){
    
        boolean success = false;        
        Grass other;
        
        if ( field() != null ){
            other = field().grass(pos);

            if ( other == null ){
                this.pos = pos;
                success = true;
            }
        }
        
        return success;
    }
    
    private void exceptionIfBatIsDestroy() {
        if ( this._isDestroy ){
            throw new IllegalStateException("Battery not exist");
        }
    }
    
}
