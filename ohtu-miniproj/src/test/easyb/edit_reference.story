import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can edit a reference in the program'

scenario "user can edit an existing reference", {
    given 'reference edit attempt', {
        references = new References()
        io = new StubIO("a", "1", "author testitys", "title", "journal", "1995", "volume", "no", "e", "testitys95", "author", "author testaus", "q")
        ui = new TextUI(references, io)
    }

    when 'valid reference name is given', {
       ui.run()
    }

    then 'reference has been edited', {
       io.getPrints().shouldHave("The field author was edited!")
    }
}

scenario "user can't edit a reference that doesn't exist", {
    given 'reference edit attempt', {
        references = new References()
        io = new StubIO("e", "testitys95", "q")
        ui = new TextUI(references, io)
    }

    when 'invalid reference name is given', {
       ui.run()
    }

    then 'reference has not been found', {
       io.getPrints().shouldHave("Reference testitys95 was not found!")
    }
}

scenario "user can't edit a field that doesn't exist", {
    given 'field edit attempt', {
        references = new References()
        io = new StubIO("a", "1", "author testitys", "title", "journal", "1995", "volume", "no", "e", "testitys95", "month", "q")
        ui = new TextUI(references, io)
    }

    when 'invalid field name is given', {
       ui.run()
    }

    then 'field has not been found', {
       io.getPrints().shouldHave("The field month was not found!")
    }
}
