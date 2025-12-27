
package person.bean;

public class Staff {
    int id;
    String name;
    int salary;
    String designation;
    
    public Staff(){
        id = 0;
        name = null;
        salary = 0;
        designation = null;
    }

    public Staff(int id, String name, int salary, String designation) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.designation = designation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", name=" + name + ", salary=" + salary + ", designation=" + designation + '}';
    }
}

