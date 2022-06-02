package tst.sample.restassured.testcases;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BasicHttpValidations {
    File regfile, logfile;

    @BeforeMethod
    public void setUpFile() throws FileNotFoundException {
       regfile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\register_payload.json");
       logfile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\login_payload.json");
       baseURI= "https://reqres.in";
    }

    @Test
    public void registerSuccessFul(){
        given()
                .contentType(ContentType.JSON)
                .body(regfile)
                .when()
                .post("/api/register")
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "registerSuccessFul")
    public void loginSuccessful(){
        given()
                .contentType(ContentType.JSON)
                .body(logfile)
                .when()
                .post("/api/login")
                .then()
                .log()
                .body()
                .assertThat()

                .statusCode(200);
    }

    @Test(dependsOnMethods = "loginSuccessful")
    public void listUsers(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/unknown")
                .then()
                .body("data[0].name", is(equalTo("cerulean")),
                        "data[0].id", is(equalTo(1)));

    }

}
