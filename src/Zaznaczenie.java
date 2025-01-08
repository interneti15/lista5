public class Zaznaczenie {
    private int startX = 0;
    private int startY = 0;
    private int width = 0;
    private int height = 0;
    private int endX = 0;
    private int endY = 0;
    private boolean ini = false;
    private static int instance = 0;
    private int id = 0;
    private MODE mode = MODE.rectangle;
    Zaznaczenie(){
        id = instance;
        instance++;
    }
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

    public void setEndCoords(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
        
        this.width = Math.abs(endX - startX);
        this.height = Math.abs(endY - startY);
    }

    public Zaznaczenie copy() {
        Zaznaczenie copied = new Zaznaczenie();
        copied.setStartCoords(this.startX, this.startY);
        copied.setEndCoords(this.endX, this.endY);
        copied.setMode(this.mode);
        copied.width = this.width;
        copied.height = this.height;
        copied.ini = this.ini;
        copied.id = this.id;
        return copied;
    }

    public Zaznaczenie(Zaznaczenie selection) {
        id = instance;
        instance++;

        this.setStartCoords(selection.startX, selection.startY);
        this.setEndCoords(selection.endX, selection.endY);
        this.setMode(selection.mode);
        this.width = selection.width;
        this.height = selection.height;
        this.ini = selection.ini;
    }

    public void firstWhenAtLines(SelectionHandler selectionHandler) {
        this.ini = true;
        int distance = 15;
        startX = distance;
        startY = distance;
        endX = selectionHandler.getWidth() - distance;
        endY = selectionHandler.getHeight() - distance;
    }

    public void changeLines(int x, int y, ClosestLine closestLine) {
        switch (closestLine){
            case top -> this.startY = y;
            case bottom -> this.endY = y;
            case left -> this.startX = x;
            case right -> this.endX = x;
        }
    }

    public ClosestLine findClosest(int x, int y) {
        int left = Math.abs(x - startX);
        int right = Math.abs(endX - x);
        int top = Math.abs(y - startY);
        int bottom = Math.abs(endY - y);

        int minimum = Math.min(left, Math.min(right, Math.min(top, bottom)));

        if (left == minimum) {
            return ClosestLine.left;
        } else if (right == minimum) {
            return ClosestLine.right;
        } else if (top == minimum) {
            return  ClosestLine.top;
        } else {
            return ClosestLine.bottom;
        }
    }

    public void revalidate() {
        System.out.println("startX=" + startX + ", endX=" + endX + ", startY" + startY + " endY" + endY);
        if (endX < startX) {
            int temp = endX;
            endX = startX;
            startX = temp;
        }
        if (endY < startY) {
            int temp = endY;
            endY = startY;
            startY = temp;
        }
        System.out.println("startX=" + startX + ", endX=" + endX + ", startY" + startY + " endY" + endY);
    }

    public int getId() {
        return id;
    }
}
