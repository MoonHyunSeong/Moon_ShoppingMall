package moon.moonshop.repository.item;

import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.item.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.*;

@Slf4j
@Repository
@Transactional
public class ItemRepository {

    private final NamedParameterJdbcTemplate template;
    private RowMapper<Item> rowMapper = BeanPropertyRowMapper.newInstance(Item.class);

    public ItemRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Item save(Item item) {
        String sql = "insert into items (item_name, seller, price, quantity, category) " +
                "values (:itemName, :seller, :price, :quantity, :category)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(item);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);

        Long key = keyHolder.getKey().longValue();
        item.setItemId(key);

        return item;
    }

    public Optional<Item> findByItem(String itemName, String seller) {
        // 이름이 중복되면 꺼내올 방법이 지금 없다.
        String sql = "select * from items " +
                "where item_name =:itemName and seller =:seller";

        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("itemName", itemName)
                    .addValue("seller", seller);
            Item item = template.queryForObject(sql, param, rowMapper);
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void updateItem(Item item/*, Integer price, Integer quantity*/) {
        //service 에서 바뀔 값을 전달해주어야 한다.
        String sql = "update items " +
                "set price =:price , quantity =:quantity " +
                "where item_name =:itemName and seller =:seller";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("price", item.getPrice())
                .addValue("quantity", item.getQuantity())
                .addValue("itemName", item.getItemName())
                .addValue("seller", item.getSeller());

        template.update(sql, param);
    }

    public void removeItem(Item item) {
        String sql = "delete from items " +
                "where item_name =:itemName and seller =:seller";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("itemName", item.getItemName())
                .addValue("seller", item.getSeller());
        template.update(sql, param);
    }

//    public Optional<Item> findByOwner(String userId) {
//        String sql = "select * from item where owner =:userId";
//
//        try {
//            Map<String, Object> param = Map.of("userId", userId);
//            Item item = template.queryForObject(sql, param, rowMapper);
//            return Optional.of(item);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }

//    public List<Item> findAll(ItemUpdateCond itemSearchCond) {
//
//    }

}
