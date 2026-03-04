import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            dept = scanner.nextLine().toUpperCase();
            System.out.println("Salary:");
            salary = scanner.nextInt();
            System.out.println("Experience:");
            exp = scanner.nextInt();
            Employee employee = new Employee(id, name,dept, salary, exp);
            employees.add(employee);
        } catch (InputMismatchException e) {
            System.out.println("Please Type a Valid Datatype");
            addEmployee(id, scanner);
        }
        catch (Exception e){
            System.out.println(e + "");
            addEmployee(id,scanner);
        }
    }


    public int deleteEmployee(int id){
        for (Employee employee: employees){
            if (employee.getId() == id){
                employees.remove(employee);
                return 1;
            }
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

    public void getAllEmployee(){
        if (!employees.isEmpty()){
            employees.forEach(System.out::println);
        }
        else {
            System.out.println("No Employees Found");
        }
    }

    public Optional<Employee> getEmployeeById(int id){
        for (Employee employee: employees){
            if (employee.getId() == id){
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }

    public void updateEmployee(int id, Scanner scanner){
        if (getEmployeeById(id).isEmpty()){
            System.out.println("No Employee Found");
        }
        else{
            Employee employee = getEmployeeById(id).orElse(new Employee(1,null,null,1,1));
            int option=0;
            while (option!=5) {
                try {
                System.out.println(" ");
                System.out.println("Enter Option to update");
                System.out.println("1.Name \n2.Department \n3.Salary \n4.Experience \n5.Exit");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("Enter name to update: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        employee.setName(name);
                        continue;
                    case 2:
                        System.out.print("Enter dept to update: ");
                        scanner.nextLine();
                        String dept = scanner.nextLine().toUpperCase();
                        employee.setDepartment(dept);
                        continue;
                    case 3:
                        System.out.print("Enter salary to update: ");
                        int salary = scanner.nextInt();
                        employee.setSalary(salary);
                        continue;
                    case 4:
                        System.out.print("Enter exp to update: ");
                        int exp = scanner.nextInt();
                        employee.setExperience(exp);
                        continue;
                    case 5:
                        break;
                    default:
                        throw new InvalidOption("Option Invalid");
                }
            } catch (InvalidOption e) {
                System.out.println(e + "");
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
        if (!employees.isEmpty()){
            while (option != 4) {
                System.out.println("""
                    1.sort by salary
                    2.sort by experience
                    3.sort by name
                    4.Exit
                    """);
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        comparator = (o1, o2) -> {
                            if (o1.getSalary() > o2.getSalary())
                                return 1;
                            else if (o1.getSalary() < o2.getSalary()) {
                                return -1;
                            }
                            else
                                return 0;
                        };
                        employees.sort(comparator);
                        employees.forEach(System.out::println);
                        continue;
                    case 2:
                        comparator = (o1, o2) -> {
                            if (o1.getExperience() > o2.getExperience())
                                return 1;
                            else if (o1.getExperience() < o2.getExperience()) {
                                return -1;
                            }
                            else
                                return 0;
                        };
                        employees.sort(comparator);
                        employees.forEach(System.out::println);
                        continue;
                    case 3:
                        comparator = Comparator.comparing(o -> o.getName().toLowerCase());
                        employees.sort(comparator);
                        employees.forEach(System.out::println);
                        continue;
                    case 4:
                        comparator = Comparator.comparing(Employee::getId);
                        employees.sort(comparator);
                        break;
                    default:
                        System.out.println("Enter a Valid Option");

                }
            }
        }else
            System.out.println("No Employees to sort!");

    }

    public void filterEmpBySalary(int salary){
        employees.stream().filter(e->e.getSalary()>salary).forEach(System.out::println);
    }

    public List<Employee> filterEmpByExperience(int exp){
        if (!employees.isEmpty())
            return employees.stream().filter(e->e.getExperience()>=exp).collect(Collectors.toList());
        else
            return null;
    }


    public List<String> getEmployeeNames(){
        if(!employees.isEmpty())
            return employees.stream().map(Employee::getName).collect(Collectors.toList());
        else
            return null;
    }

    public void transformEmployeeNames1() {
        Function<Employee, Employee> emps = (Employee employee) -> {
                employee.setName(employee.getName().toUpperCase());
                return employee;
            };
        if (!employees.isEmpty())
            employees = employees.stream().map(emps).collect(Collectors.toList());
        else
            System.out.println("Employees Empty");
    }

    public void transformEmployeeNames2() {
        Function<Employee, Employee> emps = (Employee employee) -> {
                employee.setName(employee.getName().toLowerCase());
                return employee;
        };
        if (!employees.isEmpty())
            employees = employees.stream().map(emps).collect(Collectors.toList());
        else
            System.out.println("Employees Empty");
    }



    public long getEmployeeCountByDept(String dept){
        if (!employees.isEmpty())
            return employees.stream().filter(e-> e.getDepartment().equals(dept)).count();
        else
            return -1;
    }

    public Optional<Employee> getHighestSalary(){
            return employees.stream().reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2);
        //return employees.stream().map(Employee::getSalary).reduce(0,(a, b) -> Math.max(a,b));
    }


    public void divideByExperience(int exp){
        Map<Boolean,List<Employee>> map  = employees.stream().collect(Collectors.partitioningBy(e->e.getExperience()>exp));
            System.out.println("senior:"+ map.get(true));
            System.out.println("junior:"+ map.get(false));


    }

    public Map<String,List<Employee>> groupByDept(){
        if (!employees.isEmpty())
            return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        else
            return null;
    }

    public void depSalarySummary(){
        Map<String,List<Employee>> map = groupByDept();
        if (map!=null){
            for (String s: map.keySet()){
                System.out.println("Total Salary of " + s +" : " +map.get(s).stream().map(Employee::getSalary).reduce(0,(a,b)->a+b));
                map.get(s).forEach(System.out::println);
                System.out.println(" ");
            }
        }
        else
            System.out.println("Employee empty");
    }
}
