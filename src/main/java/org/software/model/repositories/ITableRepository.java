package org.software.model.repositories;

import org.software.model.table.Table;

import java.util.List;

public interface ITableRepository extends Repository<Table, Integer> {
    List<Table> getAll();
    Table getById(Integer id);
    Table create(Table table);
    Table update(Table table);
    Table delete(Table table);
}
