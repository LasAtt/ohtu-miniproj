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

scenario "user can add a correctly formatted booklet type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "3", "title", "n", "q")
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

scenario "user can add a correctly formatted institution type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "4", "title", "n", "q")
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

scenario "user can add a correctly formatted conference type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "5", "author", "title", "title", "n", "q")
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

scenario "user can add a correctly formatted inbook type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "6", "author", "title", "12", "publisher", "1999", "n", "q")
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

scenario "user can add a correctly formatted incollection type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "7", "author", "title", "title", "publisher", "1999", "n", "q")
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

scenario "user can add a correctly formatted manual type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "8", "title", "n", "q")
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

scenario "user can add a correctly formatted mastersthesis type reference", {
    given 'reference add attempt', {
        references = new References()
        
        io = new StubIO("a", "9", "author", "title", "school", "1999", "n", "q")
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