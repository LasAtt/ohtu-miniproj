import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can list the references stored in the program'

scenario "user can list all added book type references if there are no such references", {
    given 'reference listing attempt', {
       references = new References()
       io = new StubIO("2", "4")
       ui = new TextUI(references, io)
    }

    when 'references not found', {
       ui.run()
    }

    then 'the situation is reported', {
       io.getPrints().shouldHave("No references found!")
    }
}

scenario "user can list all added book type references if there are such references", {
    given 'reference listing attempt', {
       references = new References()
       io = new StubIO("1", "test", "test", "test", "test", "2", "4")
       ui = new TextUI(references, io)
    }

    when 'references found', {
       ui.run()
    }

    then 'references are listed', {
       io.getPrints().shouldHave("All references:")
    }
}