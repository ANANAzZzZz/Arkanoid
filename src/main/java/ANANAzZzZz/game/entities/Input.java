package ANANAzZzZz.game.entities;

public class Input {
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean leftArrow;
    public boolean rightArrow;

    public Input() {
        this.z = false;
        this.x = false;
        this.c = false;
        this.leftArrow = false;
        this.rightArrow = false;
    }

    public Input(Input input) {
        this.z = input.z;
        this.x = input.x;
        this.c = input.c;
        this.leftArrow = input.leftArrow;
        this.rightArrow = input.rightArrow;
    }

    public void reset() {
        z = false;
        x = false;
        c = false;
        leftArrow = false;
        rightArrow = false;
    }
}