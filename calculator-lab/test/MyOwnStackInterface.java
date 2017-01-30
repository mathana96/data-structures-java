import java.util.ArrayList;

public interface MyOwnStackInterface
{ 
  public ArrayList<Object> stackArray = new ArrayList<>();
  public <E> E peek();
  public <E> E pop();
  public <E> E push(E item);
  public <E> E search(Object o);
  boolean empty();
}
