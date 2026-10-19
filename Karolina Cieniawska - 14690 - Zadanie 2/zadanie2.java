
public abstract class Employee {
    private String name;
    private double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() { return name; }
    public double getBaseSalary() { return baseSalary; }

    public abstract double calculateSalary(boolean isBonusMonth);
}

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double baseSalary) { super(name, baseSalary); }

    @Override
    public double calculateSalary(boolean isBonusMonth) {
        double salary = getBaseSalary();
        return isBonusMonth ? salary * 1.2 : salary;
    }
}

public class ContractorEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public ContractorEmployee(String name, double hourlyRate, int hoursWorked) {
        super(name, 0);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary(boolean isBonusMonth) {
        double salary = hourlyRate * hoursWorked;
        return isBonusMonth ? salary + 1000 : salary;
    }
}

public class InternEmployee extends Employee {
    public InternEmployee(String name) { super(name, 2000); }

    @Override
    public double calculateSalary(boolean isBonusMonth) {
        return 2000;
    }
}

public class PayrollReportPrinter {
    public void printPdfReport(Employee emp, double salary) {
        System.out.println("PDF Header: Payroll Report");
        System.out.println("Employee: " + emp.getName() + " | Salary: " + salary);
        System.out.println("PDF Footer: End of Page");
    }
}

public class PayrollSystem {
    public double calculateSalary(Employee emp, boolean isBonusMonth) {
        return emp.calculateSalary(isBonusMonth);
    }
}