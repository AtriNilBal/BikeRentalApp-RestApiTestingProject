package api.payloads;


public class ResponseObject {
    private int statusCode;
    private String responseBody;

    public ResponseObject(int statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
