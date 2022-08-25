package moon.moonshop.repository;

import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Slf4j
@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Item save(Item item) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("item").usingGeneratedKeyColumns("item_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("item_name", item.getItemName());
        parameters.put("item_price", item.getPrice());
        parameters.put("item_quantity", item.getStockQuantity());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        item.setId(key.longValue());

        return item;
    }

    public Optional<Item> findById(Long id) {
        List<Item> result = jdbcTemplate.query("select * from item where item_id = ?", itemRowMapper(), id);
        return result.stream().findAny();
    }


    public Optional<Item> findByName(String itemName) {
        List<Item> result = jdbcTemplate.query("select * from item where item_name =?", itemRowMapper(), itemName);
        return result.stream().findAny();
    }

    public List<Item> findByAll() {
        return jdbcTemplate.query("select * from item", itemRowMapper());
    }



    private RowMapper<Item> itemRowMapper() {
        //멤버 테이블에 행을 만들어주는 듯 하다.

        return (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setPrice(rs.getInt("item_price"));
            item.setStockQuantity(rs.getInt("item_quantity"));
            return item;
        };
    }
}
