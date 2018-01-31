/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mongodb.*;
import com.mongodb.connection.Connection;
import entities.Collection;
import entities.Database;
import entities.Document;

/**
 *
 * @author Jo
 */
public class MetaBaseMongoDbDAO {

    private static Connection icnx;

    public MetaBaseMongoDbDAO(Connection icnx) {

        this.icnx = icnx;
    }

    public int dropDb(Database db) {
        int liAffecte = 0;

        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.26.10.144:27017"));
            DB database = mongoClient.getDB("DB");
            database.dropDatabase();

        } catch (Exception e) {
            liAffecte = -1;
            System.out.println(e.getMessage());
        }

        return liAffecte;
    }

    public int dropCollection(Collection col) {
        int liAffecte = 0;

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.26.10.144:27017"));
        DB db = mongoClient.getDB("DB");
        DBCollection myCollection = db.getCollection("Collection");
        myCollection.drop();
        
        return liAffecte;
    }

    public int dropDocument(Document doc) {
        int liAffecte = 0;
        
//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://172.26.10.144:27017"));
//        DB db = mongoClient.getDB("DB");
//        MongoCollection<Document> collection = db.getCollection("Collection");
//        collection.deleteOne(new Document("_id", new ObjectId("57a49c6c33b10927ff09623e")));
        
        
        return liAffecte;

    }

}
