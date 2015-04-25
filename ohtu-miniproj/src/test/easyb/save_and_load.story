import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can export a save JSON file and import the same JSON file'

scenario "user can't export when there are no references", {
    given 'reference export attempt', {
       references = new References()
       io = new StubIO("s", "q")
       ui = new TextUI(references, io)
    }

    when 'references not found', {
       ui.run()
    }

    then 'the situation is reported', {
       io.getPrints().shouldHave("No references found!")
    }
}

scenario "user can export the references to a JSON file when there are references and import them back", {
    given 'export and import attempt', {
       references = new References()
       io = new StubIO("add", "0", "test testitys", "title", "1999", "publisher", "no", "s", "", "d", "testitys99", "o", "", "quit")
       ui = new TextUI(references, io)
    }

    when 'export and import are being attempted', {
       ui.run()
    }

    then 'the import is completed', {
        references = ui.getReferences();
        references.getReferences().size().shouldBe 1
        references.getReference("testitys99").shouldNotBe null
    }
}