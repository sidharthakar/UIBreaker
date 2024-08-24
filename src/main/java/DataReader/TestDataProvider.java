package DataReader;

import com.github.javafaker.Faker;

public class TestDataProvider {
    private final Faker faker;
    /*
    * @return all method return String and it will help to generate random data
    * */

    public TestDataProvider(){
        faker = new Faker();
    }

    public String FirstName(){
        return faker.name().firstName();
    }
    public String LastName(){
        return faker.name().lastName();
    }
    public String FullAddress(){
        return faker.address().fullAddress();
    }
    public String Email(){
        return faker.internet().emailAddress();
    }
    public String Phone(){
        return faker.phoneNumber().phoneNumber();
    }
    public String creditCardNumber(){
        return faker.business().creditCardNumber();
    }
    public String creditCardExpiryDate(){
        return faker.business().creditCardExpiry();
    }
    public String creditCardType(){
        return faker.business().creditCardType();
    }






}
