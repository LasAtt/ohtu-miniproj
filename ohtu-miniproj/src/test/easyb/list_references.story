import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can list the references stored in the program'

scenario "user can't list all added references if there are no such references", {
    given 'reference listing attempt', {
       references = new References()
       io = new StubIO("list", "qquit")
       ui = new TextUI(references, io)
    }

    when 'references not found', {
       ui.run()
    }

    then 'the situation is reported', {
       io.getPrints().shouldHave("No references found!")
    }
}

scenario "user can list all added references if there are such references", {
    given 'reference listing attempt', {
       references = new References()
       io = new StubIO("add", "0", "author", "title", "1999", "publisher", "no", "list", "quit")
       ui = new TextUI(references, io)
    }

    when 'references found', {
       ui.run()
    }

    then 'references are listed', {
       io.getPrints().shouldHave("Book - author99: [year: 1999 author: author publisher: publisher title: title ]")
    }
}

scenario "user can list filtered references", {
    given 'reference listing attempt', {
       references = new References()
       io = new StubIO("add", "0", "author", "title", "1999", "publisher", "no", "f", "a", "0", "2", "q", "list", "quit")
       ui = new TextUI(references, io)
    }

    when 'references found', {
       ui.run()
    }

    then 'references are listed', {
       io.getPrints().shouldHave("No references found!")
    }
}