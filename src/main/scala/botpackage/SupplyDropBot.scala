package botpackage

import java.net.URL

import com.google.gson.Gson
import com.bot4s.telegram.api.{Polling, RequestHandler, TelegramBot}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.api.declarative.{Action, CommandFilterMagnet, Commands}
import com.bot4s.telegram.methods.SendMessage
import com.bot4s.telegram.models.{Message, User}


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
      val spock = scala.io.Source.fromURL("https://api.github.com/repos/C-Jett/telegram_git_request").mkString
      val gson = new Gson
      val spock2 = gson.toJson(spock)
      if (text.charAt(0) == '/'){
        request(SendMessage(msg.source, "You did it!"))
        if (text.equals("/gitupdate")){

          request(SendMessage(msg.source, "New Update Available: " + spock2+"/nCheck the above link for version"))
        }
      }
    }

  }
}



