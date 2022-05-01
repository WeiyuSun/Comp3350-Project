package comp3350.budgetingApp.Application;

import comp3350.budgetingApp.persistence.hsqldb.CredentialPersistence;
import comp3350.budgetingApp.persistence.hsqldb.EntriesPersistence;
import comp3350.budgetingApp.persistence.hsqldb.GeneralPersistence;
import comp3350.budgetingApp.persistence.hsqldb.UserPersistence;

public class Services {
    private static UserPersistence userPersistence = null;
    private static CredentialPersistence credentialPersistence = null;
    private static EntriesPersistence entriesPersistence = null;
    private static GeneralPersistence generalPersistence = null;

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null)
            userPersistence = new UserPersistence(Main.getDatabaseName());
        return userPersistence;
    }
    public static synchronized CredentialPersistence getCredentialPersistence() {
        if (credentialPersistence == null)
            credentialPersistence = new CredentialPersistence(Main.getDatabaseName());
        return credentialPersistence;
    }
    public static synchronized EntriesPersistence getEntriesPersistence() {
        if (entriesPersistence == null)
            entriesPersistence = new EntriesPersistence(Main.getDatabaseName());
        return entriesPersistence;
    }
    public static synchronized GeneralPersistence getGeneralPersistence() {
        if (generalPersistence == null)
            generalPersistence = new GeneralPersistence(Main.getDatabaseName());
        return generalPersistence;
    }

}
