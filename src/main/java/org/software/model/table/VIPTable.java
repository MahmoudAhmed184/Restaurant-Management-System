package org.software.model.table;

public class VIPTable extends Table {
    public VIPTable(int id, int tableNumber, int capacity, String status) {
        super(id, tableNumber, capacity, status);
    }

    @Override
    public String getType() {
        return "VIP";
    }
}
