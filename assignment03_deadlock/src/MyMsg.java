import java.awt.Color;
import teachnet.view.renderer.Shape;

public class MyMsg {
//The message contains the ID of the waiting process, the ID of
//the sender and the ID of the receiver
	Color color;
	Shape shape = Shape.RHOMBUS;
	
	// the three fields of the message
	int waitingId; // the initial sender
	int senderId; // the own ID
	int receiverId; // the ID of the receiver


	public MyMsg(int waitingId, int senderId, int receiverId) {
		this.waitingId = waitingId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.color = Color.ORANGE;
	}

	public int getWaitingId() {
		return waitingId;
	}

	public int getSenderId() {
		return senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	/*
		The receiver checks whether it waits itself; if so it modifies the message
		The first component remains
		the second is substituted by its own ID
		the third is the ID of the process it is waiting for (and the message goes to)
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	*/
	public Color getColor() {
		return color;
	}

	public String toString() {
		return "(" + waitingId + "," + senderId + "," + receiverId + ")";
	}
}