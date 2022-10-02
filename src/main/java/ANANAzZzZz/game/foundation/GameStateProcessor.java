package ANANAzZzZz.game.foundation;

import ANANAzZzZz.game.entities.*;
import ANANAzZzZz.game.entities.bricks.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameStateProcessor {
    private final Random random;

    public GameStateProcessor() {
        random = new Random();
    }

    public GameState init() {
        Player player = new Player(new Point(0, -120));

        Ball ball = new Ball(10, 10, 1, 1);

        ArrayList<Brick> bricks = new ArrayList<>();
        for (int i = -9; i < 10; i++) {
            int x = i * (Brick.width + 1);
            int baseY = 110;
            int yStep = 11;

            bricks.add(new UnbreakableBrick(x, baseY));
            bricks.add(new MultiHitBrick(x, baseY - yStep));
            bricks.add(new GoldenBrick(x, baseY - yStep * 2));
            bricks.add(new SimpleBrick(x, baseY - yStep * 3));
        }

        return new GameState(400, player, bricks, ball);
    }

    public GameState update(Input input, GameState gameState) {
        gameState.ball.move();

        if (input.z || input.x) {
            hitSomeBricks(input, gameState);
        }

        if (input.c) {
            hitAllBricks(gameState);
        }

        if (!(input.leftArrow && input.rightArrow)) {
            if (input.leftArrow) {
                int leftestPlayerX = gameState.player.getCoordinate().x - Player.width / 2;
                if (leftestPlayerX > -gameState.boardLength / 2) {
                    gameState.player.move(-3);
                }
            }
            if (input.rightArrow) {
                int rightestPlayerX = gameState.player.getCoordinate().x + Player.width / 2;
                if (rightestPlayerX < gameState.boardLength / 2) {
                    gameState.player.move(3);
                }
            }
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