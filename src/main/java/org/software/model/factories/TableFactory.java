package org.software.model.factories;

import org.software.model.table.OutdoorTable;
import org.software.model.table.RegularTable;
import org.software.model.table.Table;
import org.software.model.table.VIPTable;


public class TableFactory {
    private TableFactory() {
    }

    public static Table createTable(String type, int id, int tableNumber, int capacity, String status) {
        return switch (type.toLowerCase()) {
            case "regular" -> new RegularTable(id, tableNumber, capacity, status);
            case "vip" -> new VIPTable(id, tableNumber, capacity, status);
            case "outdoor" -> new OutdoorTable(id, tableNumber, capacity, status);
            default -> throw new IllegalArgumentException("Unknown table type: " + type);
        };
    }
}
