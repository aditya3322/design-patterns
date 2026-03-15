package behavioral.patterns.command;

import java.util.Stack;

public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void execute(Command c) {
        c.execute();
        history.push(c);
    }

    public void undo() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
}