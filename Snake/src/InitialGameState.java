public class InitialGameState {
    int[] initialX;
    int[] initialY;
    int initialBodyParts;
    int initialApplesEaten;
    char initialDirection;

    public InitialGameState(int[] x, int[] y, int bodyParts, int applesEaten, char direction) {
        this.initialX = x.clone();
        this.initialY = y.clone();
        this.initialBodyParts = bodyParts;
        this.initialApplesEaten = applesEaten;
        this.initialDirection = direction;
    }
}
