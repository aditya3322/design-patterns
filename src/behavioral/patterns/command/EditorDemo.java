package behavioral.patterns.command;

public class EditorDemo {
    public static void main(String[] args) {
        Document myDoc = new Document();
        CommandHistory history = new CommandHistory();

        // User types "Hello "
        history.execute(new WriteCommand(myDoc, "Hello "));
        
        // User types "World!"
        history.execute(new WriteCommand(myDoc, "World!"));
        
        System.out.println("Current Text: " + myDoc.getText()); // Hello World!

        // User hits Undo
        System.out.println("--- Undoing last action ---");
        history.undo();
        System.out.println("Current Text: " + myDoc.getText()); // Hello 

        // User hits Undo again
        System.out.println("--- Undoing last action ---");
        history.undo();
        System.out.println("Current Text: " + myDoc.getText()); // (empty)

        // User hits Undo again (Edge case check)
        history.undo(); // Output: Nothing to undo!
    }
}