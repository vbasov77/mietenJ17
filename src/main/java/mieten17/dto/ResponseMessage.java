package mieten17.dto;

public class ResponseMessage {
    private String content;

    public ResponseMessage(String content) {
        this.content = content;
    }

    public ResponseMessage() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
