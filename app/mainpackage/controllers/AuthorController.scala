package mainpackage.controllers

import javax.inject._
import play.api._
import play.api.db._
import play.api.mvc._
import play.api.libs.json._
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import mainpackage.controllers._

@Singleton
class AuthorController @Inject()(val db: Database, val controllerComponents: ControllerComponents) extends BaseController {
  
  def getAuthors = Action {implicit request: Request[AnyContent] =>
    val gson = new GsonBuilder().setPrettyPrinting().create()
    Ok(gson.toJson(API.getAllAuthors(db)))
  }

  def getAuthor(authorID: String) = Action {implicit request: Request[AnyContent] => 
    val gson = new GsonBuilder().setPrettyPrinting().create()
    Ok(gson.toJson(API.getAuthor(db, authorID)))
  }

  def getBooksByAuthor(authorID: String) = Action {implicit request: Request[AnyContent] => 
    val gson = new GsonBuilder().setPrettyPrinting().create()
    Ok(gson.toJson(API.getBooksByAuthor(db, authorID)))
  }

  def getBookByAuthor(authorID: String, bookID: String) = Action {implicit request: Request[AnyContent] => 
    val gson = new GsonBuilder().setPrettyPrinting().create()
    Ok(gson.toJson(API.getBookByAuthor(db, bookID, authorID)))
  }

}