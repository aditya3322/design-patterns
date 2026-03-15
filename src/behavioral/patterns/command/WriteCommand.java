package behavioral.patterns.command;

public class WriteCommand implements Command {
    private Document doc;
    private String text;

    public WriteCommand(Document doc, String text) {
        this.doc = doc;
        this.text = text;
    }

    @Override
    public void execute() { doc.write(text); }

    @Override
    public void undo() { doc.delete(text.length()); }


}