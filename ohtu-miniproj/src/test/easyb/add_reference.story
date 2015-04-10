/**import com.unknownpotato.com.ohtu.miniproj.*

description 'User can add a new reference to the program'

scenario "user can add a correctly formatted book type reference", {
    given 'reference add attempt', {
        References references = new References()
        ReferenceFactory factory = new ReferenceFactory(references)
        StubIO io = new StubIO("1", "test", "test", "test", "test")
        TextUI ui = new TextUI(references, io)
    }

    when 'valid reference information is entered', {
       ui.run()
    }

    then 'new reference has been added' {
       io.getPrints().shouldHave("You have added a new book type reference with the following information:")
    }
}**/