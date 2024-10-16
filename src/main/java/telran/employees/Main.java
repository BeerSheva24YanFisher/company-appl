package telran.employees;

import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class Main {
    public static void main(String[] args) {
        Company company = new CompanyImpl();
        if (company instanceof Persistable persistable) {
            persistable.restoreFromFile("employees.data");
        }
        
        Item[] items = CompanyItems.getItems(company);
        Menu menu = new Menu("Company Application", items);
        menu.perform(new StandardInputOutput());
    }
}