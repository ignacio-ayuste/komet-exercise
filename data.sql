CREATE SCHEMA `komet`;
USE komet;

CREATE TABLE tblproductpt
(
    id            INT(5)        NOT NULL AUTO_INCREMENT COMMENT 'sequence',
    name          VARCHAR(80) COMMENT 'Product name.',
    freshCutValue DECIMAL(5, 2) NOT NULL DEFAULT 0 COMMENT 'Percentage fresh
    Cut Value.',
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8 COMMENT ='Contains information about
the products';

CREATE TABLE tblcompanypt
(
    id   INT(5) NOT NULL AUTO_INCREMENT COMMENT 'sequence',
    name VARCHAR(80) COMMENT 'Company name.',
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8 COMMENT ='Contains information about
the companies';

CREATE TABLE tblboxtypept
(
    id     INT(5)         NOT NULL AUTO_INCREMENT COMMENT 'sequence',
    code   VARCHAR(80) COMMENT 'Code box type.',
    width  DECIMAL(10, 5) NOT NULL DEFAULT 0 COMMENT 'White box type',
    height DECIMAL(10, 5) NOT NULL DEFAULT 0 COMMENT 'Height box type',
    length DECIMAL(10, 5) NOT NULL DEFAULT 0 COMMENT 'Length box type',
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8 COMMENT ='Contains information about
the boxtypes';

CREATE TABLE tblinventorypt
(
    id              INT(5)         NOT NULL AUTO_INCREMENT COMMENT 'sequence',
    boxTypeId       INT(5)         NOT NULL COMMENT 'Product box type in inventory',
    productId       INT(5)         NOT NULL COMMENT 'Product Id',
    companyId       INT(5)         NOT NULL COMMENT 'Company Id',
    cubesPerCarrier DECIMAL(19, 5) NOT NULL DEFAULT 0 COMMENT 'Cubes per
    carrier',
    pack            INT(5)         NOT NULL COMMENT 'Pack',
    basePrice       DECIMAL(10, 5) NOT NULL DEFAULT 0 COMMENT 'Base price',
    PRIMARY KEY (id),
    CONSTRAINT FK_INVENTORY_PRODUCT FOREIGN KEY (productId)
        REFERENCES tblproductpt (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT FK_INVENTORY_BOXTYPE FOREIGN KEY (boxTypeId)
        REFERENCES tblboxtypept (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT FK_INVENTORY_COMPANY FOREIGN KEY (companyId)
        REFERENCES tblcompanypt (id)
        ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8 COMMENT ='Contains information about
the inventory';

CREATE TABLE tblcustomerpt
(
    id       INT(5)        NOT NULL AUTO_INCREMENT COMMENT 'sequence',
    name     VARCHAR(80) COMMENT 'Customer name.',
    markdown DECIMAL(5, 2) NOT NULL DEFAULT 0 COMMENT 'Markdown Percentage
    discount.',
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8 COMMENT ='Contains information about
the customer';

INSERT INTO tblproductpt (id, name, freshCutValue)
VALUES (1, "Red Roses 23cm", 10),
       (2, "IL Hydrangea Blue", 15),
       (3, "Black Gira%%sol 17Inch", 45),
       (4, "&White pom 3Inch", 12),
       (5, "Achillea$ Blue 23cm", 57);

INSERT INTO tblcompanypt (id, name)
VALUES (1, "BellaFlowers"),
       (2, "SuperFlowers"),
       (3, "BeautiFlowers"),
       (4, "TheFlowers"),
       (5, "CandyFlowers");

INSERT INTO tblboxtypept (id, code, width, height, length)
VALUES (1, "1111", 12.1, 12.8, 11.2),
       (2, "2222", 11.6, 16.2, 12.7),
       (3, "3333", 15.2, 18.9, 17.4),
       (4, "4444", 13.9, 15.5, 10.8),
       (5, "5555", 14.4, 12.7, 15.5);

INSERT INTO tblinventorypt (id, boxTypeId, productId, companyId,
                            cubesPerCarrier, pack, basePrice)
VALUES (1, 1, 4, 3, 16.3, 1, 1.1),
       (2, 2, 1, 2, 13.6, 1, 1.7),
       (3, 3, 5, 5, 11.6, 1, 1.4),
       (4, 4, 2, 1, 15.2, 1, 1.3),
       (5, 5, 3, 4, 12.3, 1, 1.2);

INSERT INTO tblcustomerpt (id, name, markdown)
VALUES (1, "Luis", "5"),
       (2, "Daniel", "2"),
       (3, "William", "3"),
       (4, "Johan", "1"),
       (5, "Jessica", "4");
