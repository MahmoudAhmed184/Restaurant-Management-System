package org.software.model.table;

public class RegularTable extends Table {

    public RegularTable(int id, int tableNumber, int capacity, String status) {
        super(id, tableNumber, capacity, status);
    }

    @Override
    public String getType() {
        return "Regular";
    }
}
