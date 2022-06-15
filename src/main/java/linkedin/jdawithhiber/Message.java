package linkedin.jdawithhiber;

public class Message implements java.io.Serializable {

    private Short id;
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}


