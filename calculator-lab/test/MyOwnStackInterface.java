/**
 * @author Mathana Sreedaran
 * 
 * Interface with methods to implement my own stack using an ArrayList.
 * These methods are inspired by the original Stack interface of Java
 */
import java.util.ArrayList;

public interface MyOwnStackInterface
{ 
  public ArrayList<Object> stackArray = new ArrayList<>();
  public <E> Object peek();
  public <E> Object pop();
  public <E> void push(E item);
  public int search(Object o);
  public boolean empty();
  public int length();
  public String toString();
  public void clear();
}
