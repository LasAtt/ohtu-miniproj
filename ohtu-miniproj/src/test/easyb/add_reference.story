description 'User can add a new reference to the program'

scenario "user can add a correctly formatted book type reference", {
    given 'reference add attempt', {
       references = new References()
       references.addReference("test", "test", "test");
    }

    when 'valid reference information is entered', {
       addedReference = references.getReference("test").getName()
    }

    then 'new reference has been added' {
       addedReference.shouldHave("test")
    }
}