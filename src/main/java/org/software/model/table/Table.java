package org.software.model.table;

public abstract class Table {
    private final int id;
    private final int tableNumber;
    private final int capacity;
    private final String status;

    protected Table(int id, int tableNumber, int capacity, String status) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getStatus() {
        return status;
    }

    public abstract String getType();

    public int getId() {
        return id;
    }
}
