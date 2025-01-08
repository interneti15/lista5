public class Utils {
    public static int[] adjustToBounds(int x, int y, int width, int height) {
        int adjustedX = Math.max(0, Math.min(x, width - 1));
        int adjustedY = Math.max(0, Math.min(y, height - 1));
        return new int[]{adjustedX, adjustedY};
    }
}
