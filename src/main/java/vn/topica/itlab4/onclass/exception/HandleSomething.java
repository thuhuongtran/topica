package vn.topica.itlab4.onclass.exception;

public class HandleSomething implements MyTestException{
    @Override
    public void handleSomething() throws MyException {
        String str = "Hi hello";
        try {
            str.substring(9);
        } catch (Exception e) {
            throw new MyException("Fail at something", e);
        }
    }
}
