package groovydatastructuresalgorithms

class DammAlgorithm {

    private QuasiGroupGenerator QGG;
    private ArrayList<Integer> interim = new ArrayList<>();
    private int checkDigit;
    private int value;
    int storeIndex = 0;

    DammAlgorithm(int value, int size) {
        setValue(value);
        setQuasiGroupGenerator(size);
    }

    DammAlgorithm(int value, String size) {
        setValue(value);
        setQuasiGroupGenerator(size);
    }

    DammAlgorithm(String value, int size) {
        setValue(Integer.valueOf(value));
        setQuasiGroupGenerator(size);
    }

    DammAlgorithm(String value, String size) {
        setValue(Integer.valueOf(value));
        setQuasiGroupGenerator(size);
    }

    DammAlgorithm(int value, int size, boolean isPrintable) {
        setValue(value);
        setQuasiGroupGenerator(size, isPrintable);
    }

    DammAlgorithm(int value, String size, boolean isPrintable) {
        setValue(value);
        setQuasiGroupGenerator(size, isPrintable);
    }

    DammAlgorithm(String value, int size, boolean isPrintable) {
        setValue(Integer.valueOf(value));
        setQuasiGroupGenerator(size, isPrintable);
    }

    DammAlgorithm(String value, String size, boolean isPrintable) {
        setValue(Integer.valueOf(value));
        setQuasiGroupGenerator(size, isPrintable);
    }

    private void setValue(int value) {
        this.value = value;
    }

    private void setQuasiGroupGenerator(int size) {
        this.QGG = new QuasiGroupGenerator(size);
    }

    private void setQuasiGroupGenerator(String size) {
        this.QGG = new QuasiGroupGenerator(size);
    }

    private void setQuasiGroupGenerator(int size, boolean isPrintable) {
        this.QGG = new QuasiGroupGenerator(size, isPrintable);
    }

    private void setQuasiGroupGenerator(String size, boolean isPrintable) {
        this.QGG = new QuasiGroupGenerator(size, isPrintable);
    }

    int getValue() {
        return value;
    }

    QuasiGroupGenerator QuasiGroupGenerator() {
        return QGG;
    }

    ArrayList<Integer> getInterimValue() {
        return interim;
    }

    void PrintAllInterimValues() {
        for (int i = 0; i < interim.size(); i++) {
            System.out.print(interim.get(i));
        }
    }

    int getCheckDigit() {
        return checkDigit;
    }

    void CalculateChecksum() {
        int rowIndex = 0;
        int temp = 0;

        for(int i = 0; i < inputLength(); i++) {
            temp = QGG.getValue(rowIndex, ValueAtInputIndex(i));
            rowIndex = temp;
            interim.add(i, rowIndex);
        }
        checkDigit = QGG.getValue(temp, temp);
    }

    void Validate() {
        int idx = inputLength() - 1;
        int ValidCheckDigit = QGG.getValue(ValueAtInputIndex(idx), ValueAtInputIndex(idx));
    }

    private int inputLength() {
        return String.valueOf(getValue()).length();
    }

    private int ValueAtInputIndex(int index) {
        String aString = String.valueOf(getValue());
        char ch = aString.charAt(index);
        int val = Character.getNumericValue(ch);
        return val;
    }

    /*
    public String verify() {
       String s = String.valueOf(getValue());
       String t = String.valueOf(getCheckDigit());
       return s.concat(t);
    }*/
}
