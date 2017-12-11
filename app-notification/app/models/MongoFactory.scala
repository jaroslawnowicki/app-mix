package models

import com.mongodb.casbah.MongoClient

object MongoFactory {
  private val SERVER = "localhost"
  private val PORT   = 27017
  private val DATABASE = "portfolio"
  private val COLLECTION = "stocks"
  val connection = MongoClient(SERVER)
  val collection = connection(DATABASE)(COLLECTION)
}