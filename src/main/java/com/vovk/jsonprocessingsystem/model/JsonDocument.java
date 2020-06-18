package com.vovk.jsonprocessingsystem.model;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * @author Dmytro Vovk
 */

@Data
@NoArgsConstructor
@Document("documents")
public class JsonDocument {

    @Id
    private String id;

    private JSONObject body;

    public JsonDocument(JSONObject body) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
    }
}
