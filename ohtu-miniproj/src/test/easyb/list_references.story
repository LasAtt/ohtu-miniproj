/**import com.unknownpotato.com.ohtu.miniproj.*

description 'User can list the references stored in the program'

scenario "user can list all added book type references", {
    given 'reference listing attempt', {
       References references = new References()
        ReferenceFactory factory = new ReferenceFactory(references)
        TextUI ui = new TextUI(references)
        ConsoleIO io = new ConsoleIO()
        io.
    }

    when 'references found', {
       ui.run()
    }

    then 'references are listed' {
       io.getPrints().shouldHave("Author: testi")
    }
}*/