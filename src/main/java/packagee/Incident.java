package packagee;

public class Incident {
    private String ref;
    private String time;
    private boolean status;
    private int member_id;
    public Incident(){};
    public Incident(String ref,String time ,boolean status,int member_id){
        this.ref=ref;
        this.time=time;
        this.status=status;
        this.member_id=member_id;
    }
    public String toString(){
        return "Incident: "+ref+" at "+time+" , Status: "+status+" ,Member Id: "+member_id;
    }

    public String getRef(){return ref;}
    public String getTime(){return time;}
    public boolean getStatus(){return status;}
    public int getMemberId(){return member_id;}
}
