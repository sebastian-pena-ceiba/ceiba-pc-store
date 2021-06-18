select o.id, build_service, placement_date, shipping_date, status, tracking_code, buyer_data_id, order_price,
        b.name, b.id_number, b.email, b.phone_number, b.address
  from t_order o inner join buyer_data b on o.buyer_data_id = b.id
  where tracking_code = :trackingCode