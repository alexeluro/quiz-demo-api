package com.inspiredcoda.domain.db

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object DatabaseBuilder {

    private var database: MongoDatabase? = null

    init {
        createDatabaseInstance()
    }

    fun createDatabaseInstance(): MongoDatabase {
        if (database == null) {
            val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
            database = client.getDatabase("test") //normal java driver usage
        }

        return database!!
    }

}