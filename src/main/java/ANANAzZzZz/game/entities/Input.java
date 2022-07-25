package ANANAzZzZz.game.entities;

public class Input {
    public boolean space = false;

    public Input() {
    }

    public Input(Input input) {
        this.space = input.space;
    }

    public void reset() {
        space = false;
    }
}