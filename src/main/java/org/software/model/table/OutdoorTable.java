package org.software.model.table;

public class OutdoorTable extends Table {
    public OutdoorTable(int id, int tableNumber, int capacity, String status) {
        super(id, tableNumber, capacity, status);
    }
    @Override
    public String getType() {
        return "Outdoor";
    }
}
