import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository {
    List<Employee> employees = new ArrayList<>();

    public void addEmployee(int id,Scanner scanner){
        String name=null,dept=null;
        int salary = 0;
        int exp = 0;
        try {
            System.out.println(" ");
            System.out.println("Name:");
            scanner.nextLine();
            name = scanner.nextLine();
            System.out.println("dept:");
            scanner.nextLine();
            dept = scanner.nextLine();
            System.out.println("Salary:");
            salary = scanner.nextInt();
            System.out.println("Experience:");
            exp = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please Type a Valid Datatype");
        }
        catch (Exception e){
            System.out.println(e);
        }

        Employee employee = new Employee(id, name,dept, salary, exp);
        employees.add(employee);
    }

    public void getAllEmployee(){
        if (!employees.isEmpty()){
            employees.forEach(System.out::println);
        }
        else {
            System.out.println("No Employees Found");
        }
    }

    public Employee getEmployeeById(int id){
        for (Employee employee: employees){
            if (employee.getId() == id){
                return employee;
            }
        }
        return null;
    }

    public int deleteEmployee(int id){
        Employee employee = getEmployeeById(id);
        if (employee != null){
            employees.remove(employee);
            return 1;
        }
         return -1;
    }

    public int deleteEmployees(){
        if (!employees.isEmpty()){
            employees.clear();
            return 1;
        }
        return -1;
    }

    public  void updateEmployee(int id, Scanner scanner){
        if (getEmployeeById(id) == null){
            System.out.println("No Employee Found");
        }
        else{
            int option=0;
            while (option!=4){
                try {
                    System.out.println(" ");
                    System.out.println("Enter Option to update");
                    System.out.println("1.Name \n2.Department \n3.Salary \n4.Experience \n5.Exit");
                    option = scanner.nextInt();
                    switch (option){
                        case 1:
                            System.out.print("Enter name to update: ");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            getEmployeeById(id).setName(name);
                            continue;
                        case 2:
                            System.out.print("Enter dept to update: ");
                            scanner.nextLine();
                            String dept = scanner.nextLine();
                            getEmployeeById(id).setDepartment(dept);
                            continue;
                        case 3:
                            System.out.print("Enter salary to update: ");
                            int salary = scanner.nextInt();
                            getEmployeeById(id).setSalary(salary);
                            continue;
                        case 4:
                            System.out.print("Enter exp to update: ");
                            int exp = scanner.nextInt();
                            getEmployeeById(id).setExperience(exp);
                            continue;
                        case 5:
                            break;
                        default:
                            throw new InvalidOption("Option Invalid");
                    }
                } catch (InvalidOption e) {
                    System.out.println(e);
                }
                catch (InputMismatchException e){
                    System.out.println("Please Type a Valid Datatype");
                }
                catch (Exception e){
                    System.out.println(e + "Something went wrong");
                }
            }
        }
    }

    public void sort(Scanner scanner){
        Comparator<Employee> comparator;
        int option=0;
        while (option != 4) {
            System.out.println("1.sort by salary \n" +
                    "2.sort by experience \n" +
                    "3.sort by name\n" +
                    "4.Exit \n");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    comparator = (o1, o2) -> {
                        if (o1.getSalary() > o2.getSalary())
                            return 1;
                        return -1;

                    };
                    Collections.sort(employees, comparator);
                    employees.forEach(n -> System.out.println(n));
                    continue;
                case 2:
                    comparator = (o1, o2) -> {
                        if (o1.getExperience() > o2.getExperience())
                            return 1;
                        return -1;

                    };
                    Collections.sort(employees, comparator);
                    employees.forEach(n -> System.out.println(n));
                    continue;
                case 3:
                    comparator = Comparator.comparing(o -> o.getName().toLowerCase());
                    employees.sort(comparator);
                    employees.forEach(n -> System.out.println(n));
                    continue;
                case 4:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter a Valid Option");

            }
        }
    }

    public void filterEmpBySalary(int salary){
        employees.stream().filter(e->e.getSalary()>salary).forEach(n-> System.out.println(n));
    }

    public List<Employee> filterEmpByExperience(int exp){
        Predicate<Employee> expfil = new Predicate<Employee>() {
            public boolean test(Employee n) {
                return n.getExperience()>=exp;
            }
        };
        return employees.stream().filter(expfil).collect(Collectors.toList());
    }

    public List<String> getEmployeeNames(){
        employees.forEach(n-> System.out.println(n.getName()));
        return employees.stream().map(e->e.getName()).collect(Collectors.toList());
    }

    public void transformEmployeeNames(){
        Function<Employee ,String> f =new Function<Employee, String>() {
            @Override
            public String apply(Employee employee) {
                return employee.getName().toUpperCase();
            }
        };
        employees.stream().map(e->e.getName().toUpperCase());
    }

    public long getEmployeeByDept(String dept){
        return employees.stream().filter(e-> Objects.equals(e.getDepartment(), dept)).count();
    }
}
