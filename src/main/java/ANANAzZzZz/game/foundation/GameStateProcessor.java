package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.GameState;
import ANANAzZzZz.game.entities.Input;
import ANANAzZzZz.game.entities.bricks.*;

import java.util.Iterator;
import java.util.Random;

public class GameStateProcessor {
    private final Random random;

    public GameStateProcessor() {
        random = new Random();
    }

    public GameState init() {
        GameState gameState = new GameState();

        for (int i = -9; i < 10; i++) {
            gameState.bricks.add(new UnbreakableBrick(i * 20, 110));
            gameState.bricks.add(new MultiHitBrick(i * 20, 100));
            gameState.bricks.add(new GoldenBrick(i * 20, 90));
            gameState.bricks.add(new SimpleBrick(i * 20, 80));
        }

        return gameState;
    }

    public GameState update(Input input, GameState gameState) {
        if (input.z || input.x) {
            hitSomeBricks(input, gameState);
        }

        if (input.c) {
            hitAllBricks(gameState);
        }

        return gameState;
    }

    private void hitSomeBricks(Input input, GameState gameState) {
        int bricksCountToDelete = input.z ? 1 : 5;

        for (int i = 0; i < bricksCountToDelete; i++) {
            if (gameState.bricks.size() == 0) {
                break;
            }

            int randomNum = random.nextInt(gameState.bricks.size());
            boolean isDestroyed = gameState.bricks.get(randomNum).hit();
            if (isDestroyed) {
                gameState.bricks.remove(randomNum);
            }
        }
    }

    private void hitAllBricks(GameState gameState) {
        Iterator<Brick> i = gameState.bricks.iterator();
        while (i.hasNext()) {
            Brick b = i.next();

            boolean isDestroyed = b.hit();
            if (isDestroyed) {
                i.remove();
            }
        }
    }
}