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
