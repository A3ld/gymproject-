package SecurityHubJava;

public class Event {
private String type;
private  String level;
private String source;
private String time ;

public Event (String type , String level , String source , String time){
    this.type = type ;
    this.level = level;
    this.source = source;
    this.time = time ; 
}
public String getType(){
    return type;
}
public  String  getLevel(){
    return level;
}
public String getSource(){
    return source  ;
}
public  String getTime(){
    return time; 
}
}
