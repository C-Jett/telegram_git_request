package botpackage

import com.bot4s.telegram.api.{Polling, RequestHandler, TelegramBot}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.api.declarative.{Action, CommandFilterMagnet, Commands}
import com.bot4s.telegram.methods.SendMessage
import com.bot4s.telegram.models.Message


class SupplyDropBot(val Token: String) extends TelegramBot
  with Polling
  with Commands
{
  override val client: RequestHandler =  new ScalajHttpClient(Token)
  override def receiveMessage(msg: Message): Unit = {
    for (text <- msg.text){
      if (text.charAt(0) == '/'){
        request(SendMessage(msg.source, "It wurk"))
      }
    }
  }
}



