package au.com.telstra.simcardactivator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import au.com.telstra.simcardactivator.json.ActivationRequest;
import au.com.telstra.simcardactivator.json.InternalActivationRequest;
import au.com.telstra.simcardactivator.json.InternalActivationResponse;

@RestController
public class ActivatorController {

	private static final String internalAPIRoot = "http://localhost:8444"; // ! magic string

	private static final Logger log = LoggerFactory.getLogger(ActivatorController.class);

	@PostMapping("/")
	public InternalActivationResponse externalRequest(@RequestBody ActivationRequest incomingRequest) {
		log.info(String.format("Incoming Activation Request: %s", incomingRequest.toString()));
		
		InternalActivationRequest internalRequestBody = new InternalActivationRequest(incomingRequest.getICCID());
		
		RestTemplate restTemplate = new RestTemplate();
		InternalActivationResponse internalResponse = restTemplate.postForObject(
			String.format("%s/actuate", internalAPIRoot),
			internalRequestBody,
			InternalActivationResponse.class
		);
		
		if (internalResponse == null) {
			log.error(String.format("Internal Activation Request: %s - Error - No Response", incomingRequest.toString()));
			return null;
		}

		if (internalResponse.getSuccess() == null) {
			log.error(String.format("Internal Activation Request: %s - Error - Bad Response", incomingRequest.toString()));
			return null;
		}

		log.info(String.format("Internal Activation Request: %s - %s", incomingRequest.toString(), internalResponse.getSuccess() ? "Success" : "Failure"));

		return internalResponse;
	}

}
