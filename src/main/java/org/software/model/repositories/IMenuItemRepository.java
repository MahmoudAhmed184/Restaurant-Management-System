package org.software.model.repositories;

import org.software.model.menu.MenuItem;

import java.util.List;

public interface IMenuItemRepository extends Repository<MenuItem, Integer> {
    List<MenuItem> getAll();

    MenuItem getById(Integer id);

    MenuItem create(MenuItem menuItem);

    MenuItem update(MenuItem menuItem);

    MenuItem delete(MenuItem menuItem);
}
