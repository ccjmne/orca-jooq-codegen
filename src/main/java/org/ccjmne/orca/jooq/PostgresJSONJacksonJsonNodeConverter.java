package org.ccjmne.orca.jooq;

import java.io.IOException;

import org.jooq.Converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

@SuppressWarnings("serial")
public class PostgresJSONJacksonJsonNodeConverter implements Converter<Object, JsonNode> {

  private final ObjectMapper mapper;

  protected PostgresJSONJacksonJsonNodeConverter() {
    this.mapper = new ObjectMapper();
  }

  public JsonNode from(final Object t) {
    try {
      return t == null ? NullNode.instance : this.mapper.readTree(t.toString());
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Object to(final JsonNode u) {
    try {
      return (u == null) || u.equals(NullNode.instance) ? null : this.mapper.writeValueAsString(u);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Class<Object> fromType() {
    return Object.class;
  }

  public Class<JsonNode> toType() {
    return JsonNode.class;
  }
}
