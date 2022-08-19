DELETE FROM order_associations;
DELETE FROM orders;

INSERT INTO orders VALUES('1', '1660893861', '1660893864', 'Waiting for confirm', '0', '0', '1', '31', '2');
INSERT INTO order_associations VALUES('1', '4', '13', '1');