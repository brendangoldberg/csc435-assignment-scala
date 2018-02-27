package mainpackage.controllers

import play.api.mvc._
import play.api._
import play.api.db._
import javax.inject._
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import mainpackage.controllers.API

@Singleton
class BookController @Inject()(val db: Database, val controllerComponents: ControllerComponents) extends BaseController {
  
  def getBooks() = Action {implicit request: Request[AnyContent] =>
    val gson = new GsonBuilder().setPrettyPrinting().create()
    Ok(gson.toJson(API.getBooks(db)))
  }

  def getBook(bookID: String) = Action {implicit request : Request[AnyContent] => 
    val gson = new GsonBuilder().setPrettyPrinting().create()
    Ok(gson.toJson(API.getBook(db, bookID)))
  }

}