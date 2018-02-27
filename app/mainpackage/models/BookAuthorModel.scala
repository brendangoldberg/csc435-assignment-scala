package mainpackage.models;

import play.api.libs.json._

case class BookAuthorModel(ba_id: String, book_id: String, author_id: String) extends IModel {

  def getID() = ba_id

  def getBookID = book_id

  def getAuthorID = author_id

}

object BookAuthorModel {
  implicit val format = Json.format[BookAuthorModel]
}
