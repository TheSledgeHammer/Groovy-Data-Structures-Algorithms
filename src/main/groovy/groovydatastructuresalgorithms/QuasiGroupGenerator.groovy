package groovydatastructuresalgorithms

class QuasiGroupGenerator {

    private int size;
    private int numbersNeeded;
    private boolean isPrintable;
    private int idxZero = 0;
    private HashMap<Integer, Set<Integer>> row = new LinkedHashMap<>();

    QuasiGroupGenerator(int size) {
        setSize(size);
        setIsPrintable(false);
        createQuasiPart1();
        createQuasiPart2();

    }

    QuasiGroupGenerator(String size) {
        setSize(Integer.valueOf(size));
        setIsPrintable(false);
        createQuasiPart1();
        createQuasiPart2();
    }

    QuasiGroupGenerator(int size, boolean isPrintable) {
        setSize(size);
        setIsPrintable(isPrintable);
        createQuasiPart1();
        createQuasiPart2();
    }

    QuasiGroupGenerator(String size, boolean isPrintable) {
        setSize(Integer.valueOf(size));
        setIsPrintable(isPrintable);
        createQuasiPart1();
        createQuasiPart2();
    }

    private void setSize(int size) {
        this.size = size;
    }

    int Size() {
        return size;
    }

    String SizeToString() {
        return Integer.toString(size);
    }

    int SizeToStringLength() {
        return String.valueOf(size).length();
    }

    Set<Integer> getRow(int index) {
        return this.row.get(index);
    }

    Object[] getColumn(int index) {
        return getRow(index).toArray();
    }

    int getValue(int index1, int index2) {
        return (int) getColumn(index1)[index2];
    }

    /* Create a single Quasigroup of size */
    private Set<Integer> createQuasiPart1() {
        Random rand = new Random();
        numbersNeeded = Size();

        if(Size() < numbersNeeded) {
            throw new IllegalArgumentException("Can't ask for more numbers than are available");
        }

        Set<Integer> arr = new LinkedHashSet<>();

        while(arr.size() < numbersNeeded) {
            Integer next = rand.nextInt(Size());
            arr.add(next);
        }
        return arr;
    }

    /* Create a Quasigroup of order size aka(Latin Square)
     * Prints in a Readable Table Format
     */
    private void createQuasiPart2() {
        for(int i = 0; i < Size(); i++) {
            this.row.put(i, QuasiModification(createQuasiPart1()));
        }
    }

    /*~Hacky Approach~ To Randomized QuasiGroup:
        Switches Zero Digits in each Row to match with row and column number diagonally
        e.g. Value at Row 0 & Col 0 = 0, Row 1 & Col 1 = 0 etc
        Original value at that location are placed where 0 was.
     */
    private Set<Integer> QuasiModification(Set<Integer> oldRow) {
        LinkedList<Integer> arrL = new LinkedList<>();
        arrL.addAll(oldRow);
        for(int i = 0; i < Size(); i++) {
            if(arrL.get(i) == 0) {
                int temp = arrL.get(idxZero);
                arrL.set(idxZero, 0);
                arrL.set(i, temp);
            }
        }
        idxZero++;
        Set<Integer> newRow = new LinkedHashSet<>();
        newRow.addAll(arrL);
        Print(newRow);
        return newRow;
    }

    //Printing of QuasiGroup
    private void Print(Set<Integer> mod) {
        if(isPrintable) {
            System.out.println(mod);
        }
    }

    private void setIsPrintable(boolean isPrintable) {
        this.isPrintable = isPrintable;
    }

    boolean getIsPrintable() {
        return isPrintable;
    }
}
