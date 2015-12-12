/**
 * Assignment 01 - Implementation of flood algorithm with confirmation
 * Group A01
 * @author Philip Raschke, 333883
 * @author Pietro Rancati, 0368002
 * @author Shikhar Singh, 0368042
 * @author Paul Mälzer, 336763
 */

import teachnet.algorithm.BasicAlgorithm;

import java.awt.Color;

/**
*Implementation of flood algorithm with confirmation
*/
public class MyAlgorithm extends BasicAlgorithm {
    Color color = null;
    String caption;
    boolean informed;
    boolean isInitiator;
    int cnt;
    int markInterface = -1;

    /**
    * <p>This method runs in order to set the initial state of all nodes of the system.
    * No one is informed and colored WHITE
    * Everyone gets an id and labeled with it.
    *
    * @param config contains all the information needed to setup our graph in teachnet.
    */
    public void setup(java.util.Map<String, Object> config) {
        int id = (Integer) config.get("node.id");
        color = Color.WHITE;
        informed = false;
        isInitiator = false;
        caption = "" + id;
        cnt = 0;
    }

    /**
    * <p>This method runs on the initiator node of the system.
    * The Initiator sends to all his neighbors an Explorer message
    * It is now informed, colored RED and marked as Initiator.
    */
    public void initiate() {
        for (int i = 0; i < checkInterfaces(); ++i) {
            send(i, new MyMsg("Explorer"));
        }
        color = Color.RED;
        informed = true;
        isInitiator = true;
    }

    /**
    * This method runs when a node of the system receives a message.
    * <p>If the node is not informed and receives an explorer:
    * The node sends an Explorer to all his neighbors, excluded the node from which it received the message.
    * It is now informed and colored RED.
    * If it is a leaf it sends a Confirmation to the node from which it received the Explorer and turns GREEN.
    * <p>If the node receives a Confirmation:
    * It increments his counter of Confirmation messages
    * If this number is equals to the number of neighbors-1
    * it send a Confirmation message to node from which it received the Explorer message
    * It is now colored GREEN.
    *
    * @param  interf id of the interface from which the message was received.
    * @param  message the message received.
    */
    public void receive(int interf, Object message) {

        MyMsg msg = (MyMsg) message;
        String note = msg.getMessage();

        if (informed && note.equalsIgnoreCase("Explorer")) {
            send(interf, new MyMsg("Confirmation"));
        }

        if (!informed && note.equalsIgnoreCase("Explorer")) {
            color = msg.getColor();
            informed = true;
            markInterface = interf;

            for (int i = 0; i < checkInterfaces(); ++i) {
                if (i != interf) {
                    send(i, new MyMsg("Explorer"));
                }
            }

            if (checkInterfaces() == 1) {
                send(interf, new MyMsg("Confirmation"));
                color = Color.GREEN;
            }
        }

        if (note.equalsIgnoreCase("Confirmation")) {
            ++cnt;
            if (!isInitiator && cnt == (checkInterfaces() - 1)) {
                send(markInterface, new MyMsg("Confirmation"));
                color = Color.GREEN;
            }
            if (isInitiator && cnt == checkInterfaces()) {
                color = Color.GREEN;
            }
        }
    }
}