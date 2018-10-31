package protocol;

public class Message {
	private int length;

	private int id;
	private String content;

	public Message(int id, int length, String content) {
		this.id = id;
		this.length = length;
		this.content = content;
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

	@Override
	public String toString() {
		return String.format("[length=%d,taskId=%d,content=%s]",length,id,content);
	}
}
