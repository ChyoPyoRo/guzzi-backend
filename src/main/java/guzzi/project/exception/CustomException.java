package guzzi.project.exception;

public class CustomException extends RuntimeException{
    private ErrorCode customErrorCode;
    private String detailMessage;

    public ErrorCode getCustomErrorCode() {
        return customErrorCode;
    }

    public void setCustomErrorCode(ErrorCode customErrorCode) {
        this.customErrorCode = customErrorCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public CustomException(ErrorCode customErrorCode){
        super(customErrorCode.getDetail()); // runtimeException
        this.customErrorCode = customErrorCode;
        this.detailMessage = customErrorCode.getDetail();
    }



}