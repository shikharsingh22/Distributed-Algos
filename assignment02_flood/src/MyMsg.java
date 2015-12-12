/**
 * Assignment 02 - The message used to comunicate between node
 * Group A01
 * @author Philip Raschke, 333883
 * @author Pietro Rancati, 0368002
 * @author Shakhar Singh, 0368042
 * @author Paul Mälzer, 336763
 */

import java.awt.Color;
import java.lang.Exception;

import teachnet.view.renderer.Shape;

/**
 * This class represents the possible messages exchanged over the network
 * There are only two types of messages: Explorers in red, and Confirmations in green
 */
public class MyMsg {
    Color color;
    String message;
    Shape shape = Shape.RHOMBUS;

    /**
    * <p>Constructor method to set the message and color of MyMsg
    *   If param message is of type Explorer the color of message is RED
    *   If param message is of type Confirmation the color of message is GREEN
    *
       * @param message string containing the message.
    */
    public MyMsg(String message) {
        this.message = message;
        if (message == "Explorer") {
            this.color = Color.RED;
        }
        if (message == "Confirmation") {
            this.color = Color.GREEN;
        }
    }

    /**
    *@return The color of MyMsg
    */
    public Color getColor() {
        return color;
    }

    /**
    *@return The message of MyMsg
    */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
