/**
 * Assignment 01 - Implementation of echo algorithm
 * Group A01
 * @author Philip Raschke, 333883
 * @author Pietro Rancati, 0368002
 * @author Shikhar Singh, 0368042
 * @author Paul Mälzer, 336763
 */

import teachnet.algorithm.BasicAlgorithm;

import java.awt.*;

/**
 *Implementation of echo algorithm
 */
public class MyAlgorithm extends BasicAlgorithm {
    Color color = null;
    String caption;
    boolean informed;
    boolean isInitiator;
    int markInterface = -1;
    int cnt;
    int neighbor;

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
        caption = "" + id;
        informed = false;
        isInitiator = false;
        cnt = 0;
    }

    /**
     * <p>This method runs on the initiator node of the system.
     * The Initiator sends to all his neighbors an Explorer message
     * It is now informed, colored RED and marked as Initiator.
     */
    public void initiate() {
        color = Color.RED;
        informed = true;
        isInitiator = true;

        for (int i = 0; i < checkInterfaces(); ++i) {
            send(i, new MyMsg("Explorer"));
        }
    }

    /**
     * If the node is not informed, it changes its color to RED and changes status to 'informed'
     * It then sends an Explorer on all interfaces apart from the interface it received the Explorer from.
     * The interface from which the first explorer originated gets saved as 'activator'
     * Whenever a message is a received on a non-activated edge a counter is incremented.
     * <p> When the counter of a node equals the number of its interfaces and when the node is not the Initiator,
     * it sends an Echo on the initial activated edge and turns GREEN
     *
     * @param interf id of the interface it received the message from.
     * @param message the message it received.
     */
    public void receive(int interf, Object message) {

        if (!informed) {
            color = Color.RED;
            informed = true;
            for (int i = 0; i < checkInterfaces(); ++i) {
                if (i != interf) {
                    send(i, new MyMsg("Explorer"));
                }
            }
            neighbor = interf;
        }

        ++cnt;

        if (cnt == checkInterfaces()) {
            color = Color.GREEN;
            if (!isInitiator) {
                markInterface = neighbor;
                send(markInterface, new MyMsg("Echo"));
            }
        }
    }
}
