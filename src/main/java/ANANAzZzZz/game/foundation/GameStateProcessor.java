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
        Player player = new Player(0, -180);

        Ball ball = new Ball(-20, -50, 1, 1);

        ArrayList<Brick> bricks = new ArrayList<>();
        for (int i = -9; i < 11; i++) {
            int x = i * Brick.width - 10;
            int baseY = 194;
            int yStep = 10;

            // Unbreakable bricks
            bricks.add(new UnbreakableBrick(x, baseY));
            if (i != 5 && i != 6) {
                bricks.add(new UnbreakableBrick(x, baseY - yStep * 19));
            }
            if (i == -9 || i == 10) {
                for (int j = 1; j < 19; j++) {
                    bricks.add(new UnbreakableBrick(x, baseY - yStep * j));
                }
            }

            // Golden bricks
            if (i != -9 && i != 10) {
                bricks.add(new GoldenBrick(x, baseY - yStep));
                if (i != 5 && i != 6) {
                    bricks.add(new GoldenBrick(x, baseY - yStep * 18));
                }
            }
            if (i == -8 || i == 9) {
                for (int j = 1; j < 19; j++) {
                    bricks.add(new GoldenBrick(x, baseY - yStep * j));
                }
            }

            // Multi-hit bricks
            if (i > -8 && i < 9) {
                bricks.add(new MultiHitBrick(x, baseY - yStep * 2));
                if (i != 5 && i != 6) {
                    bricks.add(new MultiHitBrick(x, baseY - yStep * 17));
                }
            }
            if (i == -7 || i == 8) {
                for (int j = 2; j < 18; j++) {
                    bricks.add(new MultiHitBrick(x, baseY - yStep * j));
                }
            }

            // Simple bricks
            if (i > -7 && i < 8) {
                bricks.add(new SimpleBrick(x, baseY - yStep * 3));
                bricks.add(new SimpleBrick(x, baseY - yStep * 4));
                if (i == 7 || i == -6) {
                    for (int j = 3; j < 17; j++) {
                        bricks.add(new SimpleBrick(x, baseY - yStep * j));
                    }
                }
                if (i > -6 && i < 5) {
                    for (int j = 7; j < 17; j++) {
                        bricks.add(new SimpleBrick(x, baseY - yStep * j));
                    }
                }
            }
        }

        return new GameState(400, player, bricks, ball);
    }

    public GameState update(Input input, GameState gameState) {
        Point predictedBallCoordinate = gameState.ball.predictMove();
        processBoardBoundsCollision(gameState, predictedBallCoordinate);
        processBrickCollision(gameState.player, gameState.ball, predictedBallCoordinate);

        Iterator<Brick> i = gameState.bricks.iterator();
        while (i.hasNext()) {
            boolean isDestroyed = processBrickCollision(i.next(), gameState.ball, predictedBallCoordinate);
            if (isDestroyed) {
                i.remove();
            }
        }

        gameState.ball.move();

        updateDebugActions(input, gameState);

        return gameState;
    }

    private void updateDebugActions(Input input, GameState gameState) {
        if (input.s) {
            gameState.player.decreaseWidth();
        }
        if (input.w) {
            gameState.player.increaseWidth();
        }

        if (input.z || input.x) {
            hitSomeBricks(input, gameState);
        }

        if (input.c) {
            hitAllBricks(gameState);
        }

        if (!(input.leftArrow && input.rightArrow)) {
            if (input.leftArrow) {
                int leftestPlayerX = gameState.player.getCoordinate().x - gameState.player.getWidth() / 2;
                if (leftestPlayerX > -gameState.boardLength / 2) {
                    gameState.player.move(-3);
                }
            }
            if (input.rightArrow) {
                int rightestPlayerX = gameState.player.getCoordinate().x + gameState.player.getWidth() / 2;
                if (rightestPlayerX < gameState.boardLength / 2) {
                    gameState.player.move(3);
                }
            }
        }
    }

    private boolean processBrickCollision(Brick brick, Ball ball, Point predictedBallCoordinate) {
        Point topLeft = new Point(
                brick.getCoordinate().x - brick.getWidth() / 2,
                brick.getCoordinate().y + brick.getHeight() / 2
        );
        Point topRight = new Point(
                brick.getCoordinate().x + brick.getWidth() / 2,
                brick.getCoordinate().y + brick.getHeight() / 2
        );
        Point bottomLeft = new Point(
                brick.getCoordinate().x - brick.getWidth() / 2,
                brick.getCoordinate().y - brick.getHeight() / 2
        );
        Point bottomRight = new Point(
                brick.getCoordinate().x + brick.getWidth() / 2,
                brick.getCoordinate().y - brick.getHeight() / 2
        );

        if (areSegmentsIntersected(topLeft, topRight, ball.getCoordinate(), predictedBallCoordinate)) {
            ball.changeMoveDirection(1, -1);
            return brick.hit();
        }
        if (areSegmentsIntersected(topRight, bottomRight, ball.getCoordinate(), predictedBallCoordinate)) {
            ball.changeMoveDirection(-1, 1);
            return brick.hit();
        }
        if (areSegmentsIntersected(bottomRight, bottomLeft, ball.getCoordinate(), predictedBallCoordinate)) {
            ball.changeMoveDirection(1, -1);
            return brick.hit();
        }
        if (areSegmentsIntersected(bottomLeft, topLeft, ball.getCoordinate(), predictedBallCoordinate)) {
            ball.changeMoveDirection(-1, 1);
            return brick.hit();
        }

        return false;
    }

    private void processBoardBoundsCollision(GameState gameState, Point predictedBallCoordinate) {
        if (predictedBallCoordinate.x > gameState.boardLength / 2) {
            gameState.ball.changeMoveDirection(-1, 1);
        }
        if (predictedBallCoordinate.x < -gameState.boardLength / 2) {
            gameState.ball.changeMoveDirection(-1, 1);
        }
        if (predictedBallCoordinate.y > gameState.boardLength / 2) {
            gameState.ball.changeMoveDirection(1, -1);
        }
        if (predictedBallCoordinate.y < -gameState.boardLength / 2) {
            gameState.isGameOver = true;
        }
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

    // Copy-pasted from https://stackoverflow.com/a/9997374/6858443
    private boolean areSegmentsIntersected(Point segment1From, Point segment1To, Point segment2From, Point segment2To) {
        return ccw(segment1From, segment2From, segment2To) != ccw(segment1To, segment2From, segment2To) &&
                ccw(segment1From, segment1To, segment2From) != ccw(segment1From, segment1To, segment2To);
    }

    // Copy-pasted from https://stackoverflow.com/a/9997374/6858443
    // Blame author for shit naming
    private boolean ccw(Point A, Point B, Point C) {
        return (C.y - A.y) * (B.x - A.x) > (B.y - A.y) * (C.x - A.x);
    }
}