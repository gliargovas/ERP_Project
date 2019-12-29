public class Tools {

    //quickSort for int[][] array
    public static void quickSort(int arr[][], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(int arr[][], int begin, int end) {
        int pivot = arr[end][1];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j][1] <= pivot) {
                i++;

                int swapTemp = arr[i][0];
                arr[i][0] = arr[j][0];
                arr[j][0] = swapTemp;

                int swapTemp2 = arr[i][1];
                arr[i][1] = arr[j][1];
                arr[j][1] = swapTemp2;
            }
        }

        int swapTemp = arr[i+1][0];
        arr[i+1][0] = arr[end][0];
        arr[end][0] = swapTemp;

        int swapTemp2 = arr[i+1][1];
        arr[i+1][1] = arr[end][1];
        arr[end][1] = swapTemp2;

        return i+1;
    }

    //quickSort for double[][] array
    public static void quickSort(double arr[][], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(double arr[][], int begin, int end) {
        double pivot = arr[end][1];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j][1] <= pivot) {
                i++;

                double swapTemp = arr[i][0];
                arr[i][0] = arr[j][0];
                arr[j][0] = swapTemp;

                double swapTemp2 = arr[i][1];
                arr[i][1] = arr[j][1];
                arr[j][1] = swapTemp2;
            }
        }

        double swapTemp = arr[i+1][0];
        arr[i+1][0] = arr[end][0];
        arr[end][0] = swapTemp;

        double swapTemp2 = arr[i+1][1];
        arr[i+1][1] = arr[end][1];
        arr[end][1] = swapTemp2;

        return i+1;
    }
}
