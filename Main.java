import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository();
        int id = 0, option=0;


        while (option != 9) {
            try {
                System.out.println(" ");
                System.out.println("Enter Option");
                System.out.println("1.Create a New Employee \n" +
                        "2.Get Employee \n" +
                        "3.Update Employee \n" +
                        "4.Delete a Employee \n" +
                        "5.Delete All \n" +
                        "6.Display All \n" +
                        "7.sort \n" +
                        "8.others \n" +
                        "9.Exit");
                option = scanner.nextInt();
                int temp=0;
                switch (option) {
                    case 1:
                        repository.addEmployee(++id,scanner);
                        System.out.println("Added Successfully");
                        continue;
                    case 2:
                        System.out.print("Enter Id of Employee: ");
                        temp = scanner.nextInt();
                        Employee e = repository.getEmployeeById(temp);
                        if (e != null)
                            System.out.println(e.getId()+" "+e.getName()+" "+e.getSalary()+" "+e.getExperience());
                        else
                            System.out.println("Employee Not Found");
                        continue;
                    case 3:
                        System.out.print("Enter Id od Employee to Update: ");
                        temp = scanner.nextInt();
                        repository.updateEmployee(temp,scanner);
                        continue;
                    case 4:
                        System.out.print("Enter Id od Employee to delete: ");
                        temp = scanner.nextInt();
                        int res = repository.deleteEmployee(temp);
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
                    case 8:
                        System.out.println("Enter salary:");
                        int salary = scanner.nextInt();
                        repository.filterEmpBySalary(salary);
                        System.out.println("Enter exp:");
                        int exp = scanner.nextInt();
                        repository.filterEmpByExperience(exp);
                        System.out.println("Enter Dept:");
                        scanner.nextLine();
                        String dept = scanner.nextLine();
                        repository.getEmployeeByDept(dept);
                        repository.getEmployeeNames();
                        repository.transformEmployeeNames();
                        continue;
                    case 9:
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

class InvalidOption extends Exception{
    InvalidOption(String message){
        super(message);
    }
}
