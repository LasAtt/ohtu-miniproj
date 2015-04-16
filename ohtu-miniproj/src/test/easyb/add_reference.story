import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can add a new reference to the program'

scenario "user can add a correctly formatted book type reference", {
    given 'reference add attempt', {
        references = new References()
        io = new StubIO("1", "1", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "4")
        ui = new TextUI(references, io)
    }

    when 'valid reference information is entered', {
       ui.run()
    }

    then 'new reference has been added', {
       io.getPrints().shouldHave("You have added a new reference!")
    }
}