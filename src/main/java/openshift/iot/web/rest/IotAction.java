package openshift.iot.web.rest;

public class IotAction {

	private String action;
	private String key;

	public String getKey() {
		return key;
	}

	public IotAction setKey(String key) {
		this.key = key;
		return this;
	}

	public String getAction() {
		return action;
	}

	public IotAction setAction(String action) {
		this.action = action;
		return this;
	}
}
