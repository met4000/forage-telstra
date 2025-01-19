package au.com.telstra.simcardactivator.json;

public class InternalActivationRequest {
  private String iccid;

  public InternalActivationRequest(String iccid) {
    this.iccid = iccid;
  }

  public String getICCID() { return this.iccid; }
}
