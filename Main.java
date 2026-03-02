import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository();
        int id = 0, option=0;

        while (option != 9) {
            try {
                System.out.println(" ");
                System.out.println("Enter Option");
                System.out.println("""
                        1.Create a New Employee
                        2.Get Employee
                        3.Update Employee
                        4.Delete a Employee
                        5.Delete All
                        6.Display All
                        7.sort
                        8.others
                        9.Exit""");
                option = scanner.nextInt();
                int temp,res;
                switch (option) {
                    case 1:
                        repository.addEmployee(++id,scanner);
                        System.out.println("Added Successfully");
                        continue;
                    case 2:
                        System.out.print("Enter Id of Employee: ");
                        temp = scanner.nextInt();
                        Optional<Employee> e = repository.getEmployeeById(temp);
                        Employee employee;
                        if (e.isPresent()) {
                            employee = e.orElse(new Employee(1, null, null, 1, 1));
                            System.out.println(employee);
                        }
                        else
                            System.out.println("Employee not found");
                        continue;
                    case 3:
                        System.out.print("Enter Id od Employee to Update: ");
                        temp = scanner.nextInt();
                        repository.updateEmployee(temp,scanner);
                        continue;
                    case 4:
                        System.out.print("Enter Id od Employee to delete: ");
                        temp = scanner.nextInt();
                        res = repository.deleteEmployee(temp);
                        if (res==1)
                            System.out.println("Employee Deleted Successfully");
                        else
                            System.out.println("No Employee Found");
                        continue;
                    case 5:
                        res = repository.deleteEmployees();
                        if (res==1)
                            System.out.println("All Employees Deleted Successfully");
                        else
                            System.out.println("No Employees in Repository");
                        continue;
                    case 6:
                        repository.getAllEmployee();
                        continue;
                    case 7:
                        repository.sort(scanner);
                        continue;
                    case 8:
                        int otherOption = 0;
                        while (otherOption!=10){
                            System.out.println("""
                                    Enter option:\s
                                    1.filterEmpBySalary
                                    2.filterEmpByExperience
                                    3.getEmployeeCountByDept
                                    4.Get Employee Names
                                    5.Transform Employee Names to Upper Case
                                    6.Transform Employee Names to Lower Case
                                    7.Get Highest Salary
                                    8.Group Employees by Department
                                    9.Partition employees by experience
                                    10.exit""");
                            otherOption = scanner.nextInt();
                            switch (otherOption) {
                                case 1:
                                    System.out.println("Enter salary:");
                                    int salary = scanner.nextInt();
                                    repository.filterEmpBySalary(salary);
                                    continue;
                                case 2:
                                    System.out.println("Enter exp:");
                                    int exp = scanner.nextInt();
                                    List<Employee> l = repository.filterEmpByExperience(exp);
                                    l.forEach(System.out::println);
                                    continue;
                                case 3:
                                    System.out.println("Enter Dept:");
                                    scanner.nextLine();
                                    String dept = scanner.nextLine().toUpperCase();
                                    System.out.println(repository.getEmployeeCountByDept(dept));
                                    continue;
                                case 4:
                                    List<String> s= repository.getEmployeeNames();
                                    s.forEach(System.out::println);
                                    continue;
                                case 5:
                                    repository.transformEmployeeNames1();
                                    continue;
                                case 6:
                                    repository.transformEmployeeNames2();
                                    continue;
                                case 7:
                                    System.out.println(repository.getHighestSalary());
                                    continue;
                                case 8:
                                    repository.groupByDept();
                                    continue;
                                case 9:
                                    System.out.println("Enter Experience");
                                    temp = scanner.nextInt();
                                    repository.divideByExperience(temp);
                                    continue;
                                case 10:
                                    break;
                                default:
                                    throw new InvalidOption("Option Invalid");
                            }
                        }
                        continue;
                    case 9:
                        break;
                    default:
                        throw new InvalidOption("Option Invalid");
                }
            } catch (InvalidOption e) {
                System.out.println(e+ "");
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

class InvalidOption extends Exception{
    InvalidOption(String message){
        super(message);
    }
}
