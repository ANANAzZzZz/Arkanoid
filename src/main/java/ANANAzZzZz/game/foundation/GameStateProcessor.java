package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.GameState;
import ANANAzZzZz.game.entities.Input;
import ANANAzZzZz.game.entities.bricks.GoldenBrick;
import ANANAzZzZz.game.entities.bricks.SimpleBrick;
import ANANAzZzZz.game.entities.bricks.UnbreakableBrick;

import java.util.Random;

public class GameStateProcessor {
    private final Random random;

    public GameStateProcessor() {
        random = new Random();
    }

    public GameState init() {
        GameState gameState = new GameState();

        for (int i = -9; i < 10; i++) {
            gameState.bricks.add(new UnbreakableBrick(i * 2, 11));
            gameState.bricks.add(new GoldenBrick(i * 2, 10));
            gameState.bricks.add(new SimpleBrick(i * 2, 9));
        }

        return gameState;
    }

    public GameState update(Input input, GameState gameState) {
        if (input.z || input.x || input.c) {
            int bricksCountToDelete;
            if (input.z) {
                bricksCountToDelete = 1;
            } else if (input.x) {
                bricksCountToDelete = 5;
            } else {
                bricksCountToDelete = gameState.bricks.size();
            }

            for (int i = 0; i < bricksCountToDelete; i++) {
                if (gameState.bricks.size() == 0) {
                    break;
                }

                int randomNum = random.nextInt(gameState.bricks.size());
                gameState.bricks.remove(randomNum);
            }
        }

        return gameState;
    }
}
