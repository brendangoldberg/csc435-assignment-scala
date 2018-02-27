package mainpackage.controllers

import com.google.gson.Gson
import play.api.db._
import scala.collection.mutable.MutableList
import play.api.libs.json._
import play.api.db.{Database}

import mainpackage.models.{AuthorModel, BookModel}

object API {

  def getAllAuthors(db: Database) : Array[AuthorModel] = {
    val list = MutableList[AuthorModel]()
    val gson = new Gson()
    db.withConnection{conn =>
      val statement = conn.createStatement()
      val result = statement.executeQuery("""
      select *
      from author
      """
      )
      while (result.next())
        list += new AuthorModel(result.getString("author_id"), result.getString("first_name"), result.getString("last_name"))
    }
    list.toArray
  }

  def getAuthor(db: Database, authorID: String) : AuthorModel = {
    db.withConnection{conn => 
      val statement = conn.createStatement()
      val result = statement.executeQuery(s"select * from author where author_id='$authorID'")
      if (result.next()) {
        new AuthorModel(result.getString("author_id"), result.getString("first_name"), result.getString("last_name"))
      } else {
        null
      }
    }
  }

  def getBooksByAuthor(db: Database, authorID: String) : Array[BookModel] = {
    val list = MutableList[BookModel]()
    db.withConnection{conn => 
      val statement = conn.createStatement()
      val result = statement.executeQuery(s"""
      SELECT *
      FROM book b
      JOIN book_author ba ON b.book_id=ba.book_id
      JOIN author a ON a.author_id=ba.author_id
      WHERE a.author_id='$authorID'
      """)
      while(result.next()) {
        val id = result.getString("book_id")
        val title = result.getString("title")
        val genre = result.getString("genre")
        val year_published = result.getInt("year_published")
        val pages = result.getInt("pages")
        list += new BookModel(id, title, genre, year_published, pages)
      }
    }
    list.toArray
  }

  def getBookByAuthor(db: Database, bookID: String, authorID: String) : BookModel = {
    db.withConnection{conn => 
      val ba_statement = conn.createStatement()
      val ba_result = ba_statement.executeQuery(s"""
      SELECT *
      FROM book_author
      WHERE book_id='$bookID' AND author_id='$authorID'
      """)
      if (ba_result.next()) {
        val book_statement = conn.createStatement()
        val book_result = book_statement.executeQuery(s"""
        SELECT *
        FROM book
        WHERE book_id='$bookID'
        """)
        if (book_result.next()) {
          val id = book_result.getString("book_id")
          val title = book_result.getString("title")
          val genre = book_result.getString("genre")
          val year_published = book_result.getInt("year_published")
          val pages = book_result.getInt("pages")
          new BookModel(id, title, genre, year_published, pages)
        } else {
          null
        }
      } else {
        new BookModel()
      }
    }
  }

  def getBooks(db: Database) : Array[BookModel] = {
    val list = MutableList[BookModel]()
    db.withConnection{conn => 
      val statement = conn.createStatement()
      val result = statement.executeQuery(s"""
      SELECT *
      FROM book
      """)
      while (result.next()) {
        val id = result.getString("book_id")
        val title = result.getString("title")
        val genre = result.getString("genre")
        val year_published = result.getInt("year_published")
        val pages = result.getInt("pages")
        list += new BookModel(id, title, genre, year_published, pages)
      }
    }
    list.toArray
  }

  def getBook(db: Database, bookID: String) : BookModel = {
    db.withConnection{conn => 
      val statement = conn.createStatement()
      val result = statement.executeQuery(s"""
        SELECT *
        FROM book
        WHERE book_id='$bookID'
      """)
      if (result.next()) {
        val id = result.getString("book_id")
        val title = result.getString("title")
        val genre = result.getString("genre")
        val year_published = result.getInt("year_published")
        val pages = result.getInt("pages")
        new BookModel(id, title, genre, year_published, pages)
      } else {
        null
      }
    }
  }
}