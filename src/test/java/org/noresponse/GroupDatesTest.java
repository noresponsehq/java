package org.noresponse;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.noresponse.models.shared.Group;
import org.noresponse.models.shared.GroupUpdate;
import org.noresponse.utils.Utils;

class GroupDatesTest {
    @Test
    void datesPreserveOmissionAndNull() throws Exception {
        var mapper = Utils.mapper();
        var body = GroupUpdate.builder().build();
        assertFalse(mapper.valueToTree(body).has("starts_at"));
        body = body.withStartsAt(null).withEndsAt(null);
        assertTrue(mapper.valueToTree(body).get("starts_at").isNull());
        assertTrue(mapper.valueToTree(body).get("ends_at").isNull());
        body = body.withStartsAt("2026-12-03T09:00:00Z");
        assertEquals("2026-12-03T09:00:00Z", mapper.valueToTree(body).get("starts_at").asText());
        var group = mapper.readValue("{\"id\":\"grp_example\",\"environment\":\"test\",\"name\":\"Harbour building\",\"starts_at\":null,\"ends_at\":null,\"principals\":[],\"topics\":[],\"people\":0,\"version\":1,\"created_by\":\"test\",\"created_at\":\"2026-11-15T10:00:00Z\",\"updated_at\":\"2026-11-15T10:00:00Z\"}", Group.class);
        assertTrue(group.startsAt().isPresent());
        assertNull(group.startsAt().get());
        assertTrue(group.endsAt().isPresent());
        assertNull(group.endsAt().get());
        var other = mapper.readValue("{\"group_id\":\"grp_building\",\"group_name\":\"Harbour building\",\"group_starts_at\":null,\"person_id\":\"per_example\",\"matched_on\":[\"person_id\"],\"match_state\":\"unscreened\",\"highest_band\":null,\"decision\":null}", org.noresponse.models.shared.OtherGroup.class);
        assertNull(other.groupStartsAt().get());
    }
}
