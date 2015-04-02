import com.unknownpotato.com.ohtu.miniproj.*

description 'User can list the references stored in the program'

scenario "user can list all added book type references", {
    given 'reference listing attempt', {
       references = new References()
       references.addReference("test", "test", "test");
       references.addReference("test2", "test2", "test2");
       
    }

    when 'references found', {
       addedReferences = references.getReferences()
    }

    then 'references are listed' {
       addedReferences.shouldHave("test")
       addedReferences.shouldHave("test2")
    }
}