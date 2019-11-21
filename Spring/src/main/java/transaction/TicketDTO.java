package transaction;

public class TicketDTO {
   
   private String customerId;
   private int amount;
   
   public TicketDTO() {}
   public TicketDTO(String customerId, int amount) {
      super();
      this.customerId = customerId;
      this.amount = amount;
   }
   
   public String getCustomerId() {
      return customerId;
   }
   public void setCustomerId(String customerId) {
      this.customerId = customerId;
   }
   public int getAmount() {
      return amount;
   }
   public void setAmount(int amount) {
      this.amount = amount;
   }
   
  
   

}