package inventory.core.exceptions;

public class ItemAlreadyAllocatedException extends RuntimeException {

  public ItemAlreadyAllocatedException(String message){
    super(message);
  }

}
