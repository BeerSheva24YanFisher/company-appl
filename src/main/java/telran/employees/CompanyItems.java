package telran.employees;

import telran.view.InputOutput;
import telran.view.Item;

public class CompanyItems {

    private static Company company;

    public static Item[] getItems(Company company) {
        CompanyItems.company = company;
        Item[] res = {
                Item.of("Add Employee", CompanyItems::addEmployee),
                Item.of("Remove Employee", CompanyItems::removeEmployee),
                Item.of("Get Employee by ID", CompanyItems::getEmployeeById),
                Item.of("Get Department Budget", CompanyItems::getDepartmentBudget),
                Item.of("Get Departments", CompanyItems::getDepartments),
                Item.of("Get Managers with Highest Factor", CompanyItems::getManagersWithMostFactor),
                Item.of("Save to File", CompanyItems::saveToFile),
                Item.of("Restore from File", CompanyItems::restoreFromFile),
                Item.ofExit()
        };
        return res;
    }

    static void addEmployee(InputOutput io) {
        Employee employee = io.readObject(
            "Enter employee details in JSON format", 
            "Invalid employee data", 
            Employee::getEmployeeFromJSON
        );
        company.addEmployee(employee);
        io.writeLine("Employee added successfully.");
    }

    static void removeEmployee(InputOutput io) {
        long id = io.readLong("Enter employee ID", "Invalid ID. Please enter a valid employee ID.");
        Employee removed = company.removeEmployee(id);
        io.writeLine("Removed employee: " + removed);
    }

    static void getEmployeeById(InputOutput io) {
        long id = io.readLong("Enter employee ID", "Invalid ID. Please enter a valid employee ID.");
        Employee employee = company.getEmployee(id);
        io.writeLine(employee != null ? employee.toString() : "Employee not found.");
    }

    static void getDepartmentBudget(InputOutput io) {
        String department = io.readString("Enter department name");
        int budget = company.getDepartmentBudget(department);
        io.writeLine("Department budget: " + budget);
    }

    static void getDepartments(InputOutput io) {
        String[] departments = company.getDepartments();
        io.writeLine("Departments: " + String.join(", ", departments));
    }

    static void getManagersWithMostFactor(InputOutput io) {
        Manager[] managers = company.getManagersWithMostFactor();
        if (managers.length == 0) {
            io.writeLine("No managers found.");
        } else {
            for (Manager manager : managers) {
                io.writeLine(manager.toString());
            }
        }
    }

    static void saveToFile(InputOutput io) {
        String fileName = io.readString("Enter file name to save");
        CompanyImpl companyImpl = (CompanyImpl) company;
        companyImpl.saveToFile(fileName);
        io.writeLine("Company data saved to " + fileName);
    }

    static void restoreFromFile(InputOutput io) {
        String fileName = io.readString("Enter file name to restore");
        CompanyImpl companyImpl = (CompanyImpl) company;
        companyImpl.restoreFromFile(fileName);
        io.writeLine("Company data restored from " + fileName);
    }
}