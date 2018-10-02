package botpackage

import com.google.gson.Gson

import scala.concurrent.Await

object main {
  def main(args: Array[String]): Unit = {
      new SupplyDropBot(new tokens().returnToken).run()
      while (true){

      }
  }
}
