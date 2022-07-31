package ANANAzZzZz.game.entities;

public class Input {
    public boolean z;
    public boolean x;
    public boolean c;

    public Input() {
        this.z = false;
        this.x = false;
        this.c = false;
    }

    public Input(Input input) {
        this.z = input.z;
        this.x = input.x;
        this.c = input.c;
    }

    public void reset() {
        z = false;
        x = false;
        c = false;
    }
}