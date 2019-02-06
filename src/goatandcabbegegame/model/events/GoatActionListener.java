package goatandcabbegegame.model.events;

import java.util.EventListener;

/**
 *
 * @author IgorHome
 */
public interface GoatActionListener extends EventListener {

    /**
     *
     * @param e
     */
    void goatMakedMove(GoatActionEvent e);
}
