package showcase.mvc.data;

public class JavaBean {

    private String param1;
    private String param2;
    private String param3;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("JavaBean {");
        builder.append("param1='").append(param1).append('\'');
        builder.append(", param2='").append(param2).append('\'');
        builder.append(", param3='").append(param3).append('\'');
        builder.append('}');
        return builder.toString();
    }

}