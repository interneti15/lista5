public class Zaznaczenie {
    private int startX = 0;
    private int startY = 0;
    private int width = 0;
    private int height = 0;
    private int endX = 0;
    private int endY = 0;
    private boolean ini = false;
    private MODE mode = MODE.rectangle;

    public boolean isIni() {
        return ini;
    }

    public void setStartCoords(int x, int y) {
        ini = true;
        startX = x;
        startY = y;
        width = 0;
        height = 0;
    }

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    // New method to set endX and endY, while updating startX, startY, width, and height
    public void setEndCoords(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
        // Update width and height
        this.width = Math.abs(endX - startX);
        this.height = Math.abs(endY - startY);
    }

    // Copy method
    public Zaznaczenie copy() {
        Zaznaczenie copied = new Zaznaczenie();
        copied.setStartCoords(this.startX, this.startY);
        copied.setEndCoords(this.endX, this.endY);
        return copied;
    }

    public void firstWhenAtLines(SelectionHandler selectionHandler) {
        this.ini = true;
        int distance = 15;
        startX = distance;
        startY = distance;
        endX = selectionHandler.getWidth() - distance;
        endY = selectionHandler.getHeight() - distance;
    }

    public void changeLines(int x, int y) {

        int left = Math.abs(x - startX);
        int right = Math.abs(endX - x);
        int top = Math.abs(y - startY);
        int bottom = Math.abs(endY - y);

        int minimum = Math.min(left, Math.min(right, Math.min(top, bottom)));

        if (left == minimum) {
            this.startX = x;
        } else if (right == minimum) {
            this.endX = x;
        } else if (top == minimum) {
            this.startY = y;
        } else {
            this.endY = y;
        }
    }
}
