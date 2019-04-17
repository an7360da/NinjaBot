package info;
import java.io.Serializable;

public class Messages {


	/**
	 * Base class for all messages between robots. Provides basic requirements for
	 * every message.
	 * 
	 * @author Brian Norman
	 */
	public abstract class Message implements Serializable {

	   /**
	    * Determines if a deserialized file is compatible with this class.
	    * Maintainers must change this value if and only if the new version of this
	    * class is not compatible with old versions.
	    */
	   private static final long serialVersionUID = 668935086971289346L;

	   /**
	    * The name of the sender.
	    */
	   private String  sender_;

	   /**
	    * The time the message was sent.
	    */
	   private long  time_;

	   /**
	    * Creates a base message with the specified information.
	    * 
	    * @param sender
	    *           the name of the sender.
	    * @param time
	    *           the time the message was sent.
	    */
	   public Message(String sender, long time) {
	      this.sender_ = sender;
	      this.time_ = time;
	   }

	   /**
	    * Returns the name of the sender of the message.
	    * 
	    * @return the name of the sender.
	    */
	   public String getSender() {
	      return sender_;
	   }

	   /**
	    * Returns the time the message was sent.
	    * 
	    * @return the time of the message.
	    */
	   public long getTime() {
	      return time_;
	   }
	}
}
