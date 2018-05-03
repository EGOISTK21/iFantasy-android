package xyz.egoistk21.iFantasy.util;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class DBMigration implements RealmMigration {

    private static DBMigration instance = new DBMigration();

    public static DBMigration getInstance() {
        return instance;
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        schema.create("User")
                .addField("id", int.class)
                .addField("level", int.class)
                .addField("money", int.class)
                .addField("viplevel", int.class)
                .addField("nickname", String.class)
                .addField("phone", String.class)
                .addField("logintoken", String.class)
                .addField("accesstoken", String.class);
        schema.get("SimplePlayer")
                .addField("id", int.class, FieldAttribute.PRIMARY_KEY)
                .addField("bag_id", int.class)
                .addField("score", int.class)
                .addField("price", int.class)
                .addField("salary", int.class)
                .addField("name", String.class)
                .addField("pos1", String.class)
                .addField("pos2", String.class);
    }
}
