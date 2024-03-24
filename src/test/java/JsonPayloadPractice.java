import io.restassured.path.json.JsonPath;
import org.apache.groovy.json.internal.CharBuf;

import java.io.File;
import java.util.*;

public class JsonPayloadPractice {
    
//    Sample Payload
//    {
//        "id": "1",
//        "name":"Steve"
//        "Subject":["Chemistry","Maths"],
//        "Score":[
//        { "Subj":"Chemistry", "Marks":"75"},
//        { "Subj":"Maths", "Marks":"73"}]
//    }
    
    public void jsonAsString(){
        String JonPayload="{"+
        "\"id\": \"1\","+
        "\"name\":\"Steve\","+
        "\"Subject\":[\"Chemistry\",\"Maths\"],"+
        "\"Score\":["+
        "{ \"Subj\":\"Chemistry\", \"Marks\":\"75\"},"+
        "{ \"Subj\":\"Maths\", \"Marks\":\"73\"}]"+
        "}";

        JsonPath abc=new JsonPath(JonPayload);
        System.out.println(abc.getString("Subject[0]"));
        System.out.println(abc.getString("Score[1].Subj"));
        System.out.println(JonPayload);
    }

    public void jsonAsMap(){
        Map<String,Object> rootNode = new LinkedHashMap<>();
        rootNode.put("id","1");
        rootNode.put("name","Steve");

        //Creating Array using Arraylist
        List<String> subjectArray=new ArrayList<String>();
        subjectArray.add("Chemistry");
        subjectArray.add("Maths");

        //adding Subject Array to rootNode
        rootNode.put("Subject",subjectArray);

        // starting with inner child for score array
        Map<String,String> scoreArrayObj1 = new HashMap<String,String>();
        scoreArrayObj1.put("Subj","Chemistry");
        scoreArrayObj1.put("Marks","75");

        Map<String,String> scoreArrayObj2 = new HashMap<String,String>();
        scoreArrayObj2.put("Subj","Maths");
        scoreArrayObj2.put("Marks","73");

        List<Object> scoreArray=new ArrayList<>();
        scoreArray.add(scoreArrayObj1);
        scoreArray.add(scoreArrayObj2);

        //adding scoreArray to rootNode
        rootNode.put("Score",scoreArray);


        System.out.println(rootNode.get("Subject"));
        System.out.println(rootNode);
    }

    public void jsonAsFile(){
        File jsonFile=new File("D:\\IntellijWorkspace\\RestAssuredBDDProject\\src\\test\\resources\\RequestPayloads\\Sample1.json");
        JsonPath jsonPathFromFile=JsonPath.from(jsonFile);
        System.out.println(jsonFile);
        System.out.println(jsonPathFromFile.get("job").toString());
    }

    public static void main(String[] args) {
        JsonPayloadPractice j=new JsonPayloadPractice();
        j.jsonAsString();
        System.out.println("************");
        j.jsonAsMap();
        System.out.println("************");
        j.jsonAsFile();
    }
}
