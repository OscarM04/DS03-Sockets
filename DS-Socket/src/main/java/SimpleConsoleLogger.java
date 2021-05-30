public class SimpleConsoleLogger {

    private boolean showServerResponse;

    public SimpleConsoleLogger(boolean showResponses){
        this.showServerResponse = showResponses;
    }

    public void LOG(String log){
        if (this.showServerResponse) System.out.println(log);
    }
}
