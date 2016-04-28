package neo.bosm2015.bits.com.bosm2015;


public class Sport {
    private String id;
    private String name;
    private String desc;
    private String result;

    public Sport() {

    }

    public Sport(String id, String name, String desc, String result) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.result = result;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
