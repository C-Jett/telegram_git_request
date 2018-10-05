package botpackage

import java.net.URL
import java.util

import com.google.gson.Gson
import com.bot4s.telegram.api.{Polling, RequestHandler, TelegramBot}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.api.declarative.{Action, CommandFilterMagnet, Commands}
import com.bot4s.telegram.methods.{ParseMode, SendMessage}
import com.bot4s.telegram.models.{Message, User}
import com.google.gson.internal.LinkedTreeMap


class SupplyDropBot(val Token: String) extends TelegramBot
  with Polling
  with Commands
{
  override val client: RequestHandler =  new ScalajHttpClient(Token)
  override def receiveMessage(msg: Message): Unit = {
    for(users <- msg.newChatMembers){
      users.foreach((users:User) => request(SendMessage(msg.source,
          "Thanks for inviting me, type /omghelpme for commands.")))
    }
    for (text <- msg.text){
      val spock = scala.io.Source.fromURL("https://api.github.com/repos/C-Jett/telegram_git_request/branches").mkString
      val gson = new Gson()
      val sha = gson.fromJson(spock, new Array[Any](0).getClass)(0)
        .asInstanceOf[LinkedTreeMap[String, Any]].get("commit")
        .asInstanceOf[LinkedTreeMap[String,Any]].get("sha")


      if (text.charAt(0) == '/'){
        if (text.equals("/gitupdate")){
          val commits = "[Commits](https://github.com/C-Jett/telegram_git_request/commit/"+ sha+")"
          val master = "[Update](https://github.com/C-Jett/telegram_git_request/branches)"
          request(SendMessage(msg.source, "Latest Commit: "+ commits + "\n" +
            "Branches: " +master,
            Option(ParseMode.Markdown),disableWebPagePreview = Some(true)))

        }
      }
    }
  }
}



