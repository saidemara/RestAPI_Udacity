package org.example;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

public class Trello_Udacity {

    String URL= "https://api.trello.com";
    JsonPath OrgID;
    String IDCreator = "638a7abb4b5730030ca5c5b7";
    String BoradID="63c952dc40a35e01c2fa31c7";
    String ListID=  "63c958e31517ac00a452d13c";


    @Test
    public void CreateOrg()
    {
        OrgID=   given().baseUri(URL)
                .queryParam("displayName","KianOrg")
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().post(" /1/organizations").then().extract().response().body().jsonPath().prettyPeek();
        System.out.println("The Current ID is =:"+OrgID.get("id"));
        System.out.println("The Current User ID is =:"+OrgID.get("idMemberCreator"));
    }
    @Test
    public void GetOrgID()
    {
        given().baseUri(URL)
                .pathParam("id",IDCreator)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().get(" /1/members/{id}").
                then().extract().response().body().prettyPeek();
    }
    @Test
    public void DeleteOrgs()
    {
        String id = "63c9383fe3ccd602fd12ef94";
        given().baseUri(URL)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().delete(" /1/organizations/"+id).
                then().extract().response().body().prettyPeek();
    }
    @Test
    public void CreateBoard()
    {
        ResponseBody ResObj=   given().baseUri(URL)
                .queryParam("name","UAT")
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().post(" /1/boards/").
                then().extract().response().body().prettyPeek();
//        "id": "63c952dc40a35e01c2fa31c7",
//            "name": "UAT",
//            "desc": "",
//            "descData": null,
//            "closed": false,
//            "idOrganization": "63c9383fe3ccd602fd12ef94",

    }
    @Test
    public void GetBoradOFOrganazition()
    {
        String id="63c9383fe3ccd602fd12ef94";
        given().baseUri(URL)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().get(" /1/organizations/"+id+"/boards").
                then().extract().response().body().prettyPeek();
    }
    @Test
    public void CreateList()
    {
        String id="63c9383fe3ccd602fd12ef94";
        given().baseUri(URL)
                .queryParam("name","Proposed")
                .queryParam("idBoard",BoradID)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().post(" /1/lists").
                then().extract().response().body().prettyPeek();
    }
    @Test
    public void Getalllistsonaboard()
    {
        given().baseUri(URL)
//                .pathParam("id",ListID)
//                .queryParam("idBoard",BoradID)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().get(" /1/lists/"+ListID+"/board").
                then().extract().response().body().prettyPeek();
    }

    @Test
    public void Archivealist()
    {
        given().baseUri(URL)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().put("/1/lists/"+ListID+"/closed").
                then().extract().response().body().prettyPeek();
    }
    @Test
    public void Deleteaboard()
    {
        given().baseUri(URL)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().delete("/1/boards/"+BoradID).
                then().extract().response().body().prettyPeek();
    }

    public void DeleteOrganiztion()
    {
        given().baseUri(URL)
                .queryParam("key","a682a0b89f36441b6c811052993ecff6")
                .queryParam("token","ATTA5f115ea5db5d610a280c237caef8b102bf75fa596a641f8997fca4e5a6427bdaFFAAE392")
                .contentType("application/json;charset=UTF-8").log().all().when().delete("/1/boards/"+BoradID).
                then().extract().response().body().prettyPeek();
    }
}