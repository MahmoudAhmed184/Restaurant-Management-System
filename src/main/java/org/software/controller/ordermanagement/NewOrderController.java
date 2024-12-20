package org.software.controller.ordermanagement;

import org.software.model.order.Order;
import org.software.model.order.OrderManager;
import org.software.model.factories.MenuItemFactory;
import org.software.model.menu.MenuItem;
import org.software.model.repositories.MenuItemRepository;
import org.software.utils.MenuItemMapper;
import org.software.utils.OrderDetailsMapper;
import org.software.utils.OrdersMapper;
import org.software.view.ordermanagement.NewOrderFrame;
import org.software.view.ordermanagement.OrderManagementPanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.List;

public class NewOrderController {
    private final NewOrderFrame newOrderFrame;
    private final OrderManager orderManager;
    private final MenuItemRepository menuItemRepository;

    public NewOrderController(NewOrderFrame newOrderFrame) {
        this.newOrderFrame = newOrderFrame;
        this.orderManager = OrderManager.getInstance();
        this.menuItemRepository = new MenuItemRepository();
    }

    public void initialize() {
        configureNewOrderFrame();
        registerActionListener();
        loadMenuTableData();
        loadOrderTableData();
    }

    private void configureNewOrderFrame() {
        newOrderFrame.setVisible(true);
        newOrderFrame.setLocationRelativeTo(null);
        newOrderFrame.setDefaultCloseOperation(NewOrderFrame.DISPOSE_ON_CLOSE);
    }

    private void registerActionListener() {
        newOrderFrame.addAddButtonActionListener(e -> addItemToOrder());
        newOrderFrame.addRemoveButtonActionListener(e -> removeItemFromOrder());
        newOrderFrame.addConfirmButtonActionListener(e -> confirmOrder());
        newOrderFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                newOrderFrame.dispose();
                orderManager.clearOrder();
            }
        });
    }

    private void loadMenuTableData() {
        // @todo replace with call to model
        String[] menuColumns = {"Item ID", "Name", "Price", "Category"};
        List<MenuItem> menuItems = menuItemRepository.getAll();
        String[][] menuData = MenuItemMapper.mapOrderData(menuItems);
        newOrderFrame.updateMenuTable(menuColumns, menuData);
    }

    private void loadOrderTableData() {
        String[] orderColumns = {"Item ID", "Name", "Quantity"};
        String[][] orderData = {};
        newOrderFrame.updateOrderDetails(orderColumns, orderData);
    }

    private void addItemToOrder() {
        int selectedRow = newOrderFrame.getSelectedMenuTableRow();
        if (selectedRow == -1) {
            newOrderFrame.displayMessageDialog("Please select an item from the menu to add.");
            return;
        }
        MenuItem menuItem = getMenuItemFromRow(selectedRow);
        orderManager.addItemToOrder(menuItem);
        String[][] currentOrderData = OrderDetailsMapper.mapOrderData(orderManager.getCurrentOrder());
        newOrderFrame.updateOrderDetails(new String[]{"Item ID", "Name", "Quantity"}, currentOrderData);
    }

    private MenuItem getMenuItemFromRow(int selectedRow) {
        String itemId = newOrderFrame.getMenuTableValueAt(selectedRow, 0);
        String itemName = newOrderFrame.getMenuTableValueAt(selectedRow, 1);
        String itemPrice = newOrderFrame.getMenuTableValueAt(selectedRow, 2);
        String itemCategory = newOrderFrame.getMenuTableValueAt(selectedRow, 3);
        return MenuItemFactory.getMenuItem(itemCategory,
                Integer.parseInt(itemId),
                itemName,
                new BigDecimal(itemPrice));
    }

    private void removeItemFromOrder() {
        int selectedRow = newOrderFrame.getSelectedOrderTableRow();

        if (selectedRow == -1) {
            newOrderFrame.displayMessageDialog("Please select an item from the order to remove.");
            return;
        }

        String itemId = newOrderFrame.getOrderTableValueAt(selectedRow, 0);
        orderManager.removeItemFromOrder(Integer.parseInt(itemId));

        String[][] currentOrderData = OrderDetailsMapper.mapOrderData(orderManager.getCurrentOrder());
        newOrderFrame.updateOrderDetails(new String[]{"Item ID", "Name", "Quantity"}, currentOrderData);
    }

    private void confirmOrder() {
        orderManager.setOrderType(newOrderFrame.getSelectedOrderType());
        // @todo there is a fucking problem
        try {
            orderManager.setTableNumber(Integer.parseInt(newOrderFrame.getTableNumber()));
        } catch (NumberFormatException _) {

        }
        Order order = orderManager.saveOrder();
        orderManager.createNewOrder();
        newOrderFrame.dispose();
        OrderManagementPanel.getInstance().addRowToTable(OrdersMapper.mapOrderData(order));
    }
}