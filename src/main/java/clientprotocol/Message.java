package clientprotocol;

import java.io.UnsupportedEncodingException;

public class Message {
	protected int length;
	protected char messageType;
	protected int id;
	protected String content;

	public Message(final char messageType, int id, String content) throws UnsupportedEncodingException {
		this.id = id;
		this.length = content.getBytes("UTF-8").length;
		this.content = content;
		this.messageType = messageType;
	}

	public int getLength() {
		return length;
	}

	public String getContent() {
		return content;
	}

	public int getId() {
		return id;
	}

	public char getTypeTag() {
		return messageType;
	}

	@Override
	public String toString() {
		return String.format(messageType + "[length=%d,taskId=%d,content=%s]",length,id,content);
	}
}
