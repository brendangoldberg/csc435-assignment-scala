package mainpackage.models;

import play.api.libs.json._

case class AuthorModel(author_id: String, first_name: String, last_name: String) extends IModel {

  def getID() = author_id

  def getFirstName() = first_name

  def getLastName() = last_name

}

object AuthorModel {
  implicit val format = Json.format[AuthorModel]
}