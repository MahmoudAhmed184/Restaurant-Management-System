package org.software.model.menu;

import java.math.BigDecimal;

public record MenuItem(int id, String name, BigDecimal price, String category) {

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return id == ((MenuItem) o).id;
    }
}
