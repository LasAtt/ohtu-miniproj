import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can export the references to a BibTex file'

scenario "user can't export when there are no references", {
    given 'reference export attempt', {
       references = new References()
       io = new StubIO("xport", "quit")
       ui = new TextUI(references, io)
    }

    when 'references not found', {
       ui.run()
    }

    then 'the situation is reported', {
       io.getPrints().shouldHave("No references found!")
    }
}

scenario "user can export the references to a BibTex file", {
    given 'export attempt', {
       references = new References()
       io = new StubIO("add", "0", "test", "title", "author", "1999", "publisher", "no", "xport", "quit")
       ui = new TextUI(references, io)
    }

    when 'export is being attempted', {
       ui.run()
    }

    then 'the export is completed', {
       io.getPrints().shouldHave("Export complete!")
    }
}