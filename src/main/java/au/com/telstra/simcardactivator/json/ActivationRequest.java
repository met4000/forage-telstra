package au.com.telstra.simcardactivator.json;

public class ActivationRequest {
  private String iccid;
  private String customerEmail;

  public ActivationRequest(String iccid, String customerEmail) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
  }

  public String getICCID() { return this.iccid; }
  public String getCustomerEmail() { return this.customerEmail; }

  @Override
  public String toString() {
    return String.format("{ iccid: %s, customerEmail: %s }", this.iccid, this.customerEmail);
  }
}
