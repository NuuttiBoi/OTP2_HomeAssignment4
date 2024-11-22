package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;

import static com.mongodb.client.model.Filters.eq;

public class DbConnect {
    private MongoCollection<Document> collection;

    private void connectDb(){
        String uri = "mongodb+srv://nuuttiturunen:DUMGLO7BdN2yK9Th@cluster0.szliy4a.mongodb.net/test?retryWrites=true&w=majority";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Otp1");
        collection = database.getCollection("users");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }
    public void addUser(String userName, int age, String city){
        connectDb();
        Document newUser = new Document("name",userName).append("age",age).append("city",city);
        collection.insertOne(newUser);
    }

    public void deleteUser(String userName) {
        connectDb();
        Bson filter = eq("name",userName);
        collection.deleteOne(filter);
    }

    public void updateUser(String userName, int newAge, String newCity) {
        connectDb();
        Bson filter = Filters.eq("name",userName);
        System.out.println(filter);
        Bson updateAge = Updates.set("age",newAge);
        Bson updateCity = Updates.set("city",newCity);
        collection.updateOne(filter,updateAge);
        collection.updateOne(filter,updateCity);
    }

    public String readUser(String userName) {
        connectDb();
        Document result = collection.find(eq("name",userName)).first();
        return result.toString();
    }
}
