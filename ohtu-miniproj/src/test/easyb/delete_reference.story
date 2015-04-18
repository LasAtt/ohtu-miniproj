import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can delete a reference from the program'

scenario "user can delete an existing reference", {
    given 'reference delete attempt', {
        references = new References()
        io = new StubIO("1", "4", "test", "test testitys", "test", "test", "test", "1995", "test", "test", "3", "testitys95", "5")
        ui = new TextUI(references, io)
    }

    when 'valid reference name is given', {
       ui.run()
    }

    then 'reference has been deleted', {
       io.getPrints().shouldHave("Reference testitys95 was deleted!")
    }
}

scenario "user can't delete a reference that doesn't exist", {
    given 'reference delete attempt', {
        references = new References()
        io = new StubIO("1", "4", "test", "test testitys", "test", "test", "test", "1995", "test", "test", "3", "testitys1995", "5")
        ui = new TextUI(references, io)
    }

    when 'invalid reference name is given', {
       ui.run()
    }

    then 'reference has not been found', {
       io.getPrints().shouldHave("Reference testitys1995 was not found!")
    }
}