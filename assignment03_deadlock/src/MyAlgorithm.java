import teachnet.algorithm.BasicAlgorithm;

import java.awt.Color;
import java.util.Random;

import javax.swing.plaf.SliderUI;

public class MyAlgorithm extends BasicAlgorithm{
	Color color = null;
	int markInterface = -1;
	String caption;
	Random rand = null;
	
	// ID of the node
	int id =0;
	// To recognize the initiator
	boolean initiator = false;
	// If the resource is locked or not
	boolean lock = false;
	
	// In order to set the random for the timeout
	int start = 0;

	// Distinguish wheter a node is waitinng or not
	boolean waiting=false;

	public void setup(java.util.Map<String, Object> config){
		id = (Integer) config.get("node.id");
		caption = "" + id;
		rand = getRandom();

		// Set the time out to send the probe message
		start = new Random().nextInt(10);
		if(start <= 0) { start = 1; }
		setTimeout(start, "probe");
	}

	public void initiate(){
		// Initiator has the resource, is green and sets the initiator flag
		color = Color.GREEN;
		initiator=true;
		
	}

	public void receive(int interf, Object message){
		
		// If receiving the node is waiting and has not detected the deadlock
		if((waiting== true)&&(lock==false)) {
				
				// If the ID initial in the message is equal to the own ID
				// so we have a loop and therefore a deadlock
				if((((MyMsg) message).getWaitingId()==id)){
					color = Color.RED;	
					// set lock to true and send a release message after 3 seconds
					// to resolve the deadlock
					lock=true;
					setTimeout(3, "ciao");
				}else{
				
					// otherwise forward the probe message to all the other nodes
					for (int i = 0; i < checkInterfaces(); ++i) {
												
												// replace the second ID with your own
                        if (i != ((MyMsg) message).getSenderId()) {
                            send(i, new MyMsg(((MyMsg) message).getWaitingId(), id, i));
                        }
                    }
				}		
			}else{
				// otherwise do nothing
				return;
			}
	}

	public void timeout(Object message) {
		
		// the initiator does not have to send the probe
		if(initiator){
			return;
		}
		
		// if the message is "probe" then
		String messa=(String) message;
		if(messa.equals("probe")) {
		
		// set waiting flag to true
		// node gets orange
		waiting= true;	
		color = Color.ORANGE;
			// and sends the probe message
			for (int i = 0; i < checkInterfaces(); i++) {                      
                            send(i, new MyMsg(id, id, i));                      
                    }
		}else{
		// release the request
		// other wise set wating to false and turn white
		waiting= false;	
		color = Color.WHITE;
		}
	}
}
