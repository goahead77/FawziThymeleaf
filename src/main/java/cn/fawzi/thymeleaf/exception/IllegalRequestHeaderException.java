package cn.fawzi.thymeleaf.exception;

/**
 * 非法请求 header 异常
 * @author wenqi
 */
public class IllegalRequestHeaderException extends Exception{

    private String msg;

    public IllegalRequestHeaderException(String msg){
        super(msg);
        this.msg=msg;
    }

    public IllegalRequestHeaderException(){
        super();

    }

}
