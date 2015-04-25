import com.unknownpotato.ohtu.miniproj.*
import com.unknownpotato.ohtu.miniproj.domain.*
import com.unknownpotato.ohtu.miniproj.io.*
import com.unknownpotato.ohtu.miniproj.ui.*

description 'User can add a new reference to the program'

scenario "user can add a correctly formatted book type reference", {
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
       references.getReferences().size().shouldBe 1
    }
}

scenario "user can add a correctly formatted article type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "1", "author", "title", "journal", "1999", "12", "n", "q")
        ui = new TextUI(references, io)
    }

    when 'valid reference information is entered', {
       ui.run()
    }

    then 'new reference has been added', {
       io.getPrints().shouldHave("You have added a new reference!\n")
       references.getReferences().size().shouldBe 1
    }
}

scenario "user can add a correctly formatted inproceedings type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "2", "author", "title", "title", "1999", "n", "q")
        ui = new TextUI(references, io)
    }

    when 'valid reference information is entered', {
       ui.run()
    }

    then 'new reference has been added', {
       io.getPrints().shouldHave("You have added a new reference!\n")
       references.getReferences().size().shouldBe 1
    }
}