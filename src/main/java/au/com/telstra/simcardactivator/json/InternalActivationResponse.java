package au.com.telstra.simcardactivator.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalActivationResponse {
  private Boolean success;

  @JsonCreator
  public InternalActivationResponse(@JsonProperty("success") Boolean success) {
    this.success = success;
  }

  public Boolean getSuccess() { return this.success; }
}
