package willitconnect.controller.service;

import org.json.JSONObject;
import org.junit.Test;
import willitconnect.controller.model.CheckedEntry;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class VcapServicesCheckerTest {

    VcapServicesChecker checker = new VcapServicesChecker();


    @Test(expected = NullPointerException.class)
    public void itShouldComplainAboutNullVcapServices() {
        checker.check(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldComplainAboutEmptyVcapServices() {
        JSONObject services = new JSONObject();
        checker.check(services);
    }

    @Test
    public void itShouldFindOneHostnameToCheck() {
        List<CheckedEntry> shouldBeAHostName = checker.check(
                new JSONObject(VcapServicesStrings.cleardb));
        assertThat(shouldBeAHostName, hasSize(1));
        assertThat(shouldBeAHostName.get(0).getEntry(),
                is(equalTo("us-cdbr-iron-east-02.cleardb.net")));
    }

}