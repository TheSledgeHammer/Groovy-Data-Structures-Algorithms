package groovydatastructuresalgorithms

import groovy.xml.MarkupBuilder

class Testing {

    static void main(String[] args) {

        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        String carName = 'HSV Maloo'
        xml.model() {
            car(name: carName, make: 'Holden', year: 2006) {
                country('Australia')
                record(type: 'speed', 'Production Pickup Truck with speed of 271kph')
            }
        }
        def records = new XmlParser().parseText(writer.toString())
        println writer.toString()

        CuckooHashMap<Integer, Integer> CHM = new CuckooHashMap<>();
        CHM.put(0,1);
        CHM.put(1,2);
        CHM.put(2,3);
        CHM.put(3,4);

        println CHM.indexOfKey(2)
    }
}