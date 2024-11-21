package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
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
    public void addUser(){
        connectDb();
        Document newUser = new Document("name","nuutti").append("age",24).append("city","Helsinki");
        collection.insertOne(newUser);
    }

    public void deleteUser(String userName) {
        connectDb();
        Bson filter = eq("name",userName);
        collection.deleteOne(filter);
    }

    public void updateUser() {
    }

    public String readUser(String userName) {
        connectDb();
        Document result = collection.find(eq("name",userName)).first();
        return result.toString();
    }
}
