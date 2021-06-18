select id, build_service, placement_date, shipping_date, status, tracking_code, buyer_data_id, order_price
  from t_order where id = :id