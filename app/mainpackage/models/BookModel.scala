package mainpackage.models;

import play.api.libs.json._

case class BookModel(book_id: String, title: String, genre: String, year_published: Int, pages: Int) extends IModel {
  
  def this() {
    this("", "", "", -1, -1)
  }

  def getID() = book_id

  def getTitle() = title

  def getGenre() = genre

  def getYearPublished() = year_published

  def getPages() = pages

}

object BookModel {
  implicit val format = Json.format[BookModel]
}
