package Week9.ClassPrac;
public class WarehouseGridSummary {

    public static Object[] warehouseSummary(int[][] grid) {
        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int items = grid[row][col];
                totalItems += items;

                if (items > maxItems) {
                    maxItems = items;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        return new Object[]{
            totalItems,
            new int[]{maxRow, maxCol}
        };
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        Object[] result = warehouseSummary(grid);
        int[] coordinate = (int[]) result[1];

        System.out.println("(" + result[0] + ", (" +
                coordinate[0] + ", " + coordinate[1] + "))");
    }
}