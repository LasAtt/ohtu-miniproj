import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can delete a reference from the program'

scenario "user can delete an existing reference", {
    given 'reference delete attempt', {
        references = new References()
        io = new StubIO("a", "1", "author testitys", "title", "journal", "1995", "volume", "no", "d", "testitys95", "l", "q")
        ui = new TextUI(references, io)
    }

    when 'valid reference name is given', {
       ui.run()
    }

    then 'reference has been deleted', {
       io.getPrints().shouldHave("Reference testitys95 was deleted!")
       io.getPrints().shouldHave("No references found!")
    }
}

scenario "user can't delete a reference that doesn't exist", {
    given 'reference delete attempt', {
        references = new References()
        io = new StubIO("d", "testitys95", "q")
        ui = new TextUI(references, io)
    }

    when 'invalid reference name is given', {
       ui.run()
    }

    then 'reference has not been found', {
       io.getPrints().shouldHave("Reference testitys95 was not found!")
    }
}

