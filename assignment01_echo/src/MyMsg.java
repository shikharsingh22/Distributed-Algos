/**
 * Assignment 01
 * Group A01
 * Philip Raschke, 333883
 * Pietro Rancati, 0368002
 * Shakhar Singh, 0368042
 * Paul Mälzer, 336763
 */
import teachnet.view.renderer.Shape;

import java.awt.*;

/**
 * This class represents the possible messages exchanged over the network
 * There are only two types of messages: Explorers in red, and Echos in green
 */
public class MyMsg {
    String note;
    Color color;
    Shape shape = Shape.RHOMBUS;


    /**
     * <p>Constructor method to set the message and color of MyMsg
     *   If param message is of type Explorer the color of message is RED
     *   If param message is of type Echo the color of message is GREEN
     *
     * @param message string containing the message.
     */
    public MyMsg(String message) {
        this.note = message;

        if(message.equalsIgnoreCase("Explorer")){
            this.color = Color.RED;
        }
        if(message.equalsIgnoreCase("Echo")){
            this.color = Color.GREEN;
        }
    }

    /**
     *@return The message of MyMsg
     */
    @Override
    public String toString() {
        return note;
    }
}