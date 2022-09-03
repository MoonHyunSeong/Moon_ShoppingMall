package moon.moonshop.repository.order;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.item.Item;
import moon.moonshop.domain.member.Member;
import moon.moonshop.domain.order.Order;
import moon.moonshop.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@Repository
public class OrderRepository {

    private NamedParameterJdbcTemplate template;

    private RowMapper<Order> rowMapper = BeanPropertyRowMapper.newInstance(Order.class);

    @Autowired
    public OrderRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Order order(Order order) {
        //order dto 가 필요하다. 서비스에서 form 데이터를 dto로 받고 order에 박아서 여기로 전달.
        String sql = "insert into orders (buyer, itemname, order_stock, total_price, timestamp, status) " +
                "value(:buyer, :itemName, :orderStock, :totalPrice, :timeStamp, :status)";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("buyer", order.getBuyer())
                .addValue("itemName", order.getItemName())
                .addValue("orderStock", order.getOrderStock())
                .addValue("totalPrice", order.getTotalPrice())
                .addValue("timeStamp", order.getTimestamp())
                .addValue("status", order.getStatus());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);

        Long key = keyHolder.getKey().longValue();
        order.setOrderId(key);

        return order;
    }

    public Optional<Order> findByOrder(Member member) {
        String sql = "select * " +
                "from orders " +
                "where buyer =:userId";

        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("userId", member.getUserId());
            Order order = template.queryForObject(sql, param, rowMapper);
            return Optional.of(order);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void removeOrder(Member member) {
        String sql = "select memberid " +
                "from members " +
                "where user_id =:userId";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", member.getUserId());

        template.update(sql, param);
    }
}
