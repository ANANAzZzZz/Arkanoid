package ANANAzZzZz.game.entities;

public class Input {
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean w;
    public boolean s;
    public boolean leftArrow;
    public boolean rightArrow;
    public boolean space;

    public Input() {
        this.z = false;
        this.x = false;
        this.c = false;
        this.w = false;
        this.s = false;
        this.leftArrow = false;
        this.rightArrow = false;
        this.space = false;
    }

    public Input(Input input) {
        this.z = input.z;
        this.x = input.x;
        this.c = input.c;
        this.w = input.w;
        this.s = input.s;
        this.leftArrow = input.leftArrow;
        this.rightArrow = input.rightArrow;
        this.space = input.space;
    }

    public void reset() {
        z = false;
        x = false;
        c = false;
        w = false;
        s = false;
        space = false;
    }
}