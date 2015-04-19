import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can add a new reference to the program'

scenario "user can add a correctly formatted reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "0", "author", "title", "1999", "publisher", "n", "q")
        ui = new TextUI(references, io)
    }

    when 'valid reference information is entered', {
       ui.run()
    }

    then 'new reference has been added', {
       io.getPrints().shouldHave("You have added a new reference!\n")
    }
}