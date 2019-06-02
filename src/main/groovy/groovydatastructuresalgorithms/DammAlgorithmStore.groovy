package groovydatastructuresalgorithms

class DammAlgorithmStore {
    private DammAlgorithm DA;
    private ArrayList<DammAlgorithm> dataStore = new ArrayList<>();

    DammAlgorithmStore(DammAlgorithm DA) {
        this.DA = DA;
        add(DA);
    }

    DammAlgorithmStore() {
        this.DA = null;
    }

    void add(DammAlgorithm DA) {
        dataStore.add(DA);
    }

    void remove(int index) {
        dataStore.remove(index);
    }

    ArrayList<DammAlgorithm> getData() {
        return dataStore;
    }

    DammAlgorithm getData(int index) {
        return dataStore.get(index);
    }
}
