/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatandcabbegegame.model;

import goatandcabbegegame.model.navigation.CellPosition;
import goatandcabbegegame.model.navigation.MiddlePosition;

/**
 * Абстрактный класс описывающий объект на поле
 * @author Igor
 */
public abstract class ObjectOnField {
    
    /**
     *
     */
    protected CellPosition pos = null;
    
    /**
     *
     * @return
     */
    public CellPosition globalPosition(){
        return this.pos;
    }
 
    // ------------------- -----------------
    private GameField _field = null;
    /**
     * Функция установки на поле
     * @param field 
     */
    public void setField(GameField field){
        
        if( field != null && ( field instanceof GameField)){
            this._field = field;
        }
    }
    /**
     * Функция возвращает поле
     * @return 
     */
    public GameField field(){
        return this._field;
    }
}
