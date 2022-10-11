
package entity;

/*@author pupil */
public class Reader {
    private String firstname;
    private String lastname;

    public Reader() {
    }

    public Reader(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Reader{" + "firstname=" + firstname + ", lastname=" + lastname + '}';
    }
    
    
}
