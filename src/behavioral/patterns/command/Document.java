package behavioral.patterns.command;

public class Document {
    private StringBuilder content = new StringBuilder();

    public void write(String text) { content.append(text); }
    public void delete(int length) {
        content.delete(content.length() - length, content.length());
    }
    public String read() { return content.toString(); }
    public String getText() {
        return content.toString();
    }
}