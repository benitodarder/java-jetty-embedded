-- Sample values
INSERT INTO UNIT (NAME, ABBREVIATION, enabled) VALUES ('grams','g', true)
INSERT INTO UNIT (NAME, ABBREVIATION, enabled) VALUES ('kilograms','kg', false)
INSERT INTO UNIT (NAME, ABBREVIATION, enabled) VALUES ('liters','l', true)
INSERT INTO UNIT (NAME, ABBREVIATION, enabled) VALUES ('mililiters','ml', false)
INSERT INTO COMPONENT (UNITID,NAME, enabled) VALUES ((SELECT id from unit WHERE abbreviation = 'g'),'sugar', true)
INSERT INTO COMPONENT (UNITID,NAME, enabled) VALUES ((SELECT id from unit WHERE abbreviation = 'kg'),'potatoes', false)
INSERT INTO COMPONENT (UNITID,NAME, enabled) VALUES ((SELECT id from unit WHERE abbreviation = 'l'),'water', true)
INSERT INTO COMPONENT (UNITID,NAME, enabled) VALUES ((SELECT id from unit WHERE abbreviation = 'ml'),'shampoo', false)
INSERT INTO COMPONENT (UNITID,NAME, enabled) VALUES ((SELECT id from unit WHERE abbreviation = 'g'),'coffee', true)
INSERT INTO PRODUCT (NAME,DESCRIPTION, enabled) VALUES ('coffe','a cup', false)
INSERT INTO ASSEMBLY (COMPONENTID,PRODUCTID,QUANTITY, enabled) VALUES ((SELECT id from component WHERE name = 'sugar'),(SELECT id from product WHERE name = 'coffe'),4.0, true)
INSERT INTO ASSEMBLY (COMPONENTID,PRODUCTID,QUANTITY, enabled) VALUES ((SELECT id from component WHERE name = 'coffee'),(SELECT id from product WHERE name = 'coffe'),8.3, false);
